package plumy.dsl

import mindustry.entities.bullet.BulletType
import mindustry.type.Item
import mindustry.type.Liquid
import mindustry.world.blocks.defense.turrets.ContinuousLiquidTurret
import mindustry.world.blocks.defense.turrets.ItemTurret

fun ItemTurret.addAmmo(
    item: Item, bullet: BulletType,
) {
    ammoTypes.put(item, bullet)
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

fun ContinuousLiquidTurret.removeAmmo(
    fluid: Liquid,
) {
    ammoTypes.remove(fluid)
}