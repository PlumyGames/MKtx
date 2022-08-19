package plumy.dsl

import mindustry.entities.bullet.BulletType
import mindustry.type.Item
import mindustry.type.Liquid
import mindustry.world.blocks.defense.turrets.ContinuousLiquidTurret
import mindustry.world.blocks.defense.turrets.ItemTurret
import mindustry.world.blocks.defense.turrets.LiquidTurret

fun ItemTurret.addAmmo(
    item: Item, bullet: BulletType,
) {
    ammoTypes.put(item, bullet)
}

fun LiquidTurret.addAmmo(
    liquid: Liquid, bullet: BulletType,
) {
    ammoTypes.put(liquid, bullet)
}

fun ContinuousLiquidTurret.addAmmo(
    fluid: Liquid, bullet: BulletType,
) {
    ammoTypes.put(fluid, bullet)
}

fun ItemTurret.removeAmmo(
    item: Item,
) {
    ammoTypes.remove(item)
}

fun LiquidTurret.removeAmmo(
    liquid: Liquid
) {
    ammoTypes.remove(liquid)
}

fun ContinuousLiquidTurret.removeAmmo(
    fluid: Liquid,
) {
    ammoTypes.remove(fluid)
}