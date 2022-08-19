package plumy.dsl

import mindustry.gen.Building
import mindustry.world.Block

inline fun <reified B, reified T> Block.config(
    crossinline onConfig: B.(T) -> Unit,
) where B : Building {
    when (T::class.java) {
        Int::class.java -> {
            config(java.lang.Integer::class.java) { build: B, v ->
                onConfig(build, v.toInt() as T)
            }
        }
        Byte::class.java -> {
            config(java.lang.Byte::class.java) { build: B, v ->
                onConfig(build, v.toByte() as T)
            }
        }
        Short::class.java -> {
            config(java.lang.Short::class.java) { build: B, v ->
                onConfig(build, v.toShort() as T)
            }
        }
        Long::class.java -> {
            config(java.lang.Long::class.java) { build: B, v ->
                onConfig(build, v.toLong() as T)
            }
        }
        Float::class.java -> {
            config(java.lang.Float::class.java) { build: B, v ->
                onConfig(build, v.toFloat() as T)
            }
        }
        Double::class.java -> {
            config(java.lang.Double::class.java) { build: B, v ->
                onConfig(build, v.toDouble() as T)
            }
        }
        else -> {
            config(T::class.java) { build: B, v ->
                onConfig(build, v)
            }
        }
    }
}

inline fun <reified B> Block.configNull(
    crossinline onClear: B.() -> Unit,
) where B : Building {
    configClear<B> {
        it.onClear()
    }
}