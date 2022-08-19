package plumy.animation

import arc.graphics.Color
import arc.util.Time
import plumy.core.arc.LerpType
import plumy.core.arc.hsvLerp

class AnimatedColor(
    val colorSeq: Array<Color>,
    val duration: Float = 60f,
    val lerp: LerpType = LerpType.RGB,
    val useGlobalTime: Boolean = false,
) {
    var time = 0f
        set(value) {
            field = value.coerceAtLeast(0f)
        }

    fun spend(delta: Float) {
        time += delta
    }
    /**
     * Get the current color.
     *
     * NOTE: It's an expensive call, please cache the return value.
     */
    val color = Color()
        get() {
            val time = if (useGlobalTime) Time.globalTime else time
            val count = (time / duration).toInt() % colorSeq.size
            val curIndex = count.coerceIn(0, colorSeq.size - 1)
            val nextIndex = if (curIndex == colorSeq.size - 1) 0 else curIndex + 1
            val progress = (time % duration) / duration
            when (lerp) {
                LerpType.RGB -> field.set(colorSeq[curIndex]).lerp(colorSeq[nextIndex], progress)
                LerpType.HSV -> field.set(colorSeq[curIndex]).hsvLerp(colorSeq[nextIndex], progress)
            }
            return field
        }
}