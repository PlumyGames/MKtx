package plumy.dsl

import arc.struct.Seq
import arc.util.ArcRuntimeException
import mindustry.content.TechTree
import mindustry.ctype.UnlockableContent
import mindustry.game.Objectives
import mindustry.type.ItemStack

inline fun Seq<TechTree.TechNode>.modify(
    config: TechtreeModification.() -> Unit,
) {
    TechtreeModification(this).config()
}

inline fun CreateTechTree(
    origin: UnlockableContent,
    name: String,
    requireUnlock: Boolean = false,
    config: TechTreeDeclaration.() -> Unit,
): TechTree.TechNode {
    val root = TechTree.TechNode(null, origin, origin.researchRequirements())
    root.name = name
    root.requiresUnlock = requireUnlock
    TechTree.roots.add(root)
    val declaration = TechTreeDeclaration(root)
    declaration.config()
    return root
}

class NoSuchTechNodeException(msg: String) : ArcRuntimeException(msg)
@JvmInline
value class TechtreeModification(
    val all: Seq<TechTree.TechNode>,
) {
    fun at(
        content: UnlockableContent,
        genChild: TechTree.TechNode.() -> Unit,
    ): TechTree.TechNode {
        val cur = content.techNode ?: throw NoSuchTechNodeException(content.name)
        cur.genChild()
        return cur
    }

    fun at(
        name: String,
        genChild: TechTree.TechNode.() -> Unit,
    ): TechTree.TechNode {
        val cur = TechTree.all.find { it.content.name == name } ?: throw NoSuchTechNodeException(name)
        cur.genChild()
        return cur
    }

    inline fun TechTree.TechNode.node(
        content: UnlockableContent,
        requirements: Array<ItemStack> = content.researchRequirements(),
        objectives: Array<Objectives.Objective>? = null,
        genChild: TechTree.TechNode.() -> Unit = {},
    ): TechTree.TechNode {
        val node = TechTree.TechNode(this, content, requirements)
        if (objectives != null) {
            node.objectives.addAll(*objectives)
        }
        node.genChild()
        return node
    }

    inline fun TechTree.TechNode.node(
        content: UnlockableContent,
        vararg requirements: Any,
        overwriteReq: Boolean = false,
        genChild: TechTree.TechNode.() -> Unit = {},
    ): TechTree.TechNode {
        val req = requirements.filterIsInstance<ItemStack>()
        val reqStacks = requirements.filterIsInstance<Array<ItemStack>>()
        val allReq = (req + reqStacks.flatMap { it.toList() }).toMutableList()
        if (!overwriteReq)
            allReq += content.researchRequirements()
        val node = TechTree.TechNode(this, content, allReq.toTypedArray())
        node.genChild()
        return node
    }
}
@JvmInline
value class TechTreeDeclaration(
    val root: TechTree.TechNode,
) {
    inline fun node(
        content: UnlockableContent,
        requirements: Array<ItemStack> = content.researchRequirements(),
        objectives: Array<Objectives.Objective>? = null,
        genChild: TechTree.TechNode.() -> Unit = {},
    ): TechTree.TechNode {
        val node = TechTree.TechNode(root, content, requirements)
        if (objectives != null) {
            node.objectives.addAll(*objectives)
        }
        node.genChild()
        return node
    }

    inline fun node(
        content: UnlockableContent,
        vararg requirements: Any,
        overwriteReq: Boolean = false,
        genChild: TechTree.TechNode.() -> Unit = {},
    ): TechTree.TechNode {
        val req = requirements.filterIsInstance<ItemStack>()
        val reqStacks = requirements.filterIsInstance<Array<ItemStack>>()
        val allReq = (req + reqStacks.flatMap { it.toList() }).toMutableList()
        if (!overwriteReq)
            allReq += content.researchRequirements()
        val node = TechTree.TechNode(root, content, allReq.toTypedArray())
        node.genChild()
        return node
    }

    inline fun TechTree.TechNode.node(
        content: UnlockableContent,
        requirements: Array<ItemStack> = content.researchRequirements(),
        objectives: Array<Objectives.Objective>? = null,
        genChild: TechTree.TechNode.() -> Unit = {},
    ): TechTree.TechNode {
        val node = TechTree.TechNode(this, content, requirements)
        if (objectives != null) {
            node.objectives.addAll(*objectives)
        }
        node.genChild()
        return node
    }

    inline fun TechTree.TechNode.node(
        content: UnlockableContent,
        vararg requirements: Any,
        overwriteReq: Boolean = false,
        genChild: TechTree.TechNode.() -> Unit = {},
    ): TechTree.TechNode {
        val req = requirements.filterIsInstance<ItemStack>()
        val reqStacks = requirements.filterIsInstance<Array<ItemStack>>()
        val allReq = (req + reqStacks.flatMap { it.toList() }).toMutableList()
        if (!overwriteReq)
            allReq += content.researchRequirements()
        val node = TechTree.TechNode(this, content, allReq.toTypedArray())
        node.genChild()
        return node
    }
}

fun Array<out Any>.filterObjectives(): List<Objectives.Objective> =
    this.filterIsInstance<UnlockableContent>().map {
        it.toObjective()
    } + this.filterIsInstance<Objectives.Objective>()

fun UnlockableContent.toObjective() =
    Objectives.Research(this)
