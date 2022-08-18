package plumy.dsl

import mindustry.world.blocks.defense.turrets.Turret
import mindustry.world.draw.DrawBlock
import mindustry.world.draw.DrawMulti
import mindustry.world.draw.DrawRegion
import mindustry.world.draw.DrawTurret

// DrawMulti
inline fun Turret.drawMulti(
    config: DrawMultiSpec.() -> Unit,
) {
    drawer = DrawMulti(*DrawMultiSpec().apply(config).all.toTypedArray())
}

class DrawMultiSpec {
    val all = ArrayList<DrawBlock>()
    val then = this
    infix fun add(drawer: DrawBlock) {
        all += drawer
    }

    inline fun drawTurret(
        config: DrawTurret.() -> Unit,
    ) {
        all += DrawTurret().apply(config)
    }

    inline fun drawRegion(
        suffix: String = "",
        config: DrawRegion.() -> Unit,
    ) {
        all += DrawRegion(suffix).apply(config)
    }
}
