package plumy.dsl

import mindustry.entities.Effect

inline fun NewEffect(
    duration: Float,
    clipSize: Float = 50f,
    crossinline render: Effect.EffectContainer.() -> Unit,
) = Effect(duration, clipSize) {
    it.render()
}