package plumy.core.arc

import arc.graphics.Color
import plumy.core.math.Progress
import plumy.core.math.lerp

val EmptyColor = Color()
private val hsvTemp1 = FloatArray(3)
private val hsvTemp2 = FloatArray(3)
/**
 * @return self
 */
fun Color.hsvLerp(target: Color, progress: Progress) = this.apply {
    val hsvA = this.toHsv(hsvTemp1)
    val hsvB = target.toHsv(hsvTemp2)
    hsvA.lerp(hsvB, progress)
    this.fromHsv(hsvA)
}

fun String.tinted(color: Color) =
    "[#${color}]$this[]"

enum class LerpType {
    RGB, HSV
}

fun Color(hex: String): Color = Color.valueOf(hex)
fun ColorRGB(rgb: Int): Color = Color().a(1f).rgb888(rgb)
fun ColorRGBA(rgba: Int): Color = Color().rgba8888(rgba)
fun Color.darken(percentage: Float): Color {
    r *= 1f - percentage
    g *= 1f - percentage
    b *= 1f - percentage
    return this
}

fun Color.lighten(strength: Float): Color {
    r *= 1f - strength
    g *= 1f - strength
    b *= 1f - strength
    r += strength
    g += strength
    b += strength
    return this
}