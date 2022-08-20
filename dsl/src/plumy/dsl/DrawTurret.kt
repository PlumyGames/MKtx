package plumy.dsl

import mindustry.entities.part.*
import mindustry.entities.part.DrawPart.PartProgress
import mindustry.world.blocks.defense.turrets.Turret
import mindustry.world.draw.DrawTurret

inline fun Turret.drawTurret(
    config: DrawTurret.() -> Unit,
) {
    this.drawer = DrawTurret().apply(config)
}

inline fun DrawTurret(
    config: DrawTurret.() -> Unit,
) = DrawTurret().apply(config)

fun RegionPart.addMove(
    progress: PartProgress = PartProgress.warmup,
    x: Float = 0f,
    y: Float = 0f,
    rot: Float = 0f,
) {
    moves.add(DrawPart.PartMove(progress, x, y, rot))
}
// DrawTurret
inline fun DrawTurret.regionPart(
    suffix: String = "",
    config: RegionPart.() -> Unit,
) {
    parts.add(RegionPart(suffix).apply(config))
}

inline fun DrawTurret.shapePart(
    config: ShapePart.() -> Unit,
) {
    parts.add(ShapePart().apply(config))
}

inline fun DrawTurret.flarePart(
    config: FlarePart.() -> Unit,
) {
    parts.add(FlarePart().apply(config))
}

inline fun DrawTurret.hoverPart(
    config: HoverPart.() -> Unit,
) {
    parts.add(HoverPart().apply(config))
}

inline fun DrawTurret.haloPart(
    config: HaloPart.() -> Unit,
) {
    parts.add(HaloPart().apply(config))
}
// RegionPart's children
inline fun RegionPart.regionPart(
    suffix: String = "",
    config: RegionPart.() -> Unit,
) {
    children.add(RegionPart(suffix).apply(config))
}

inline fun RegionPart.shapePart(
    config: ShapePart.() -> Unit,
) {
    children.add(ShapePart().apply(config))
}

inline fun RegionPart.flarePart(
    config: FlarePart.() -> Unit,
) {
    children.add(FlarePart().apply(config))
}

inline fun RegionPart.hoverPart(
    config: HoverPart.() -> Unit,
) {
    children.add(HoverPart().apply(config))
}

inline fun RegionPart.haloPart(
    config: HaloPart.() -> Unit,
) {
    children.add(HaloPart().apply(config))
}