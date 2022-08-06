package plumy.world

import arc.graphics.Color
import mindustry.gen.Building
import mindustry.ui.Bar
import mindustry.world.Block

inline fun <reified T : Building> Block.AddBar(
    key: String,
    crossinline name: T.() -> String,
    crossinline color: T.() -> Color,
    crossinline fraction: T.() -> Float,
    crossinline config: Bar.() -> Unit = {},
) {
    addBar<T>(key) {
        Bar(
            { it.name() },
            { it.color() },
            { it.fraction() }
        ).apply(config)
    }
}
