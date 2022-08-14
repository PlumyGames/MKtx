package plumy.world

import mindustry.gen.Building
import mindustry.world.Block

inline fun <reified B, reified T> Block.config(
    crossinline onConfig: (B, T) -> Unit,
) where B : Building {
    when (T::class.java) {
        Int::class.java -> {
            config(java.lang.Integer::class.java) { build: B, v ->
                onConfig(build, v.toInt() as T)
            }
        }
        else -> {
            config(T::class.java) { build: B, v ->
                onConfig(build, v)
            }
        }
    }
}