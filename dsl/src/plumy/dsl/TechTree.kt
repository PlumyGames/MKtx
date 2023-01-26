@file:Suppress("FunctionName", "unused")

package plumy.dsl

import arc.scene.style.Drawable
import arc.struct.ObjectFloatMap
import arc.util.ArcRuntimeException
import mindustry.content.TechTree
import mindustry.ctype.UnlockableContent
import mindustry.game.Objectives
import mindustry.type.Item
import mindustry.type.ItemStack
import mindustry.type.Planet

fun TechTree.TechNode.find(
    content: UnlockableContent,
    config: TechNodeSpec.() -> Unit,
): TechTree.TechNode {
    val cur = children.find { it.content == content } ?: throw NoSuchTechNodeException(content.name)
    TechNodeSpec(cur).config()
    return cur
}

fun TechTree.TechNode.find(
    name: String,
    config: TechNodeSpec.() -> Unit,
): TechTree.TechNode {
    val cur = children.find { it.content.name == name } ?: throw NoSuchTechNodeException(name)
    TechNodeSpec(cur).config()
    return cur
}

inline fun CreateTechTree(
    name: String,
    origin: UnlockableContent,
    requireUnlock: Boolean = false,
    icon: Drawable? = null,
    researchCostMultipliers: ObjectFloatMap<Item>? = null,
    planet: Planet? = null,
    config: TechNodeSpec.() -> Unit,
): TechTree.TechNode {
    val root = TechTree.TechNode(null, origin, origin.researchRequirements())
    root.icon = icon
    root.name = name
    root.planet = planet
    if (researchCostMultipliers != null) {
        root.researchCostMultipliers = researchCostMultipliers
    }
    root.requiresUnlock = requireUnlock
    TechTree.roots.add(root)
    val declaration = TechNodeSpec(root)
    declaration.config()
    return root
}

class NoSuchTechNodeException(msg: String) : ArcRuntimeException(msg)

@JvmInline
value class TechNodeSpec(
    val node: TechTree.TechNode,
) {
    inline operator fun UnlockableContent.invoke(
        /**
         * Type: [ItemStack], [Array]<[ItemStack]> or [Objectives.Objective].
         *
         * @see [filterObjectives]
         */
        require: List<Any>? = null,
        /**
         * - If [overwriteReq] is false, [require] will be merged into default.
         * - If [overwriteReq] is true, [require] will replace default.
         */
        overwriteReq: Boolean = false,
        icon: Drawable? = null,
        researchCostMultipliers: ObjectFloatMap<Item>? = null,
        genChild: TechNodeSpec.() -> Unit = {},
    ): TechTree.TechNode {
        val allItemReq = mutableListOf<ItemStack>()
        val objectiveReq = mutableListOf<Objectives.Objective>()
        if (require != null) {
            allItemReq.addAll(require.filterIsInstance<ItemStack>())
            for (itemStacks in require.filterIsInstance<Array<ItemStack>>()) {
                allItemReq.addAll(itemStacks)
            }
            objectiveReq.addAll(require.filterObjectives())
        }
        if (!overwriteReq) {
            allItemReq += researchRequirements()
        }
        val child = TechTree.TechNode(node, this, allItemReq.toTypedArray())
        if (overwriteReq) {
            child.objectives.clear()
        }
        child.objectives.addAll(objectiveReq)
        child.icon = icon
        if (researchCostMultipliers != null) {
            child.researchCostMultipliers = researchCostMultipliers
        }
        TechNodeSpec(child).genChild()
        return child
    }
}

fun List<Any>.filterObjectives(): List<Objectives.Objective> =
    this.filterIsInstance<UnlockableContent>().map {
        it.toObjective()
    } + this.filterIsInstance<Objectives.Objective>()

fun UnlockableContent.toObjective() =
    Objectives.Research(this)
