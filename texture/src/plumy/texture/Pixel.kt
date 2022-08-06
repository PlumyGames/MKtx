package plumy.texture

import arc.graphics.Color

@JvmInline
value class Pixel(
    val rgba8888: Int,
) {
    constructor(r: Int, g: Int, b: Int, a: Int) : this(
        (r shl 24) or
                (g shl 16) or
                (b shl 8) or
                a
    )

    constructor(r: Float, g: Float, b: Float, a: Float) : this(
        (r * 255).toInt(),
        (g * 255).toInt(),
        (b * 255).toInt(),
        (a * 255).toInt()
    )

    val isEmpty get() = rgba8888 == Empty
    val r get() = rgba8888 and RED_MASK shr 24
    val g get() = rgba8888 and GREEN_MASK shr 16
    val b get() = rgba8888 and BLUE_MASK shr 8
    val a get() = rgba8888 and ALPHA_MASK
    val rf get() = (rgba8888 and RED_MASK shr 24).to255 / 255f
    val gf get() = (rgba8888 and GREEN_MASK shr 16) / 255f
    val bf get() = (rgba8888 and BLUE_MASK shr 8) / 255f
    val af get() = (rgba8888 and ALPHA_MASK) / 255f
    val hasAlpha get() = a != 0xFF
    val isBlack get() = rgba8888 == 0x00_00_00_FF
    val isVisible get() = a != 0x00
    infix fun r(r: Int) = Pixel(r, g, b, a)
    infix fun g(g: Int) = Pixel(r, g, b, a)
    infix fun b(b: Int) = Pixel(r, g, b, a)
    infix fun a(a: Int) = Pixel(r, g, b, a)
    infix fun r(r: Float) = Pixel((r * 255).toInt(), g, b, a)
    infix fun g(g: Float) = Pixel(r, (g * 255).toInt(), b, a)
    infix fun b(b: Float) = Pixel(r, g, (b * 255).toInt(), a)
    infix fun a(a: Float) = Pixel(r, g, b, (a * 255).toInt())
    fun coverBy(b: Pixel): Pixel = if (b.isVisible) b else this
    fun alphaMixBy(): Pixel {
        return this
    }

    override fun toString(): String {
        return "(r=${r.to255},g=${g.to255},b=${b.to255},a=${a.to255})"
    }

    val toHex: String
        get() {
            val builder = StringBuilder()
            builder.append(Integer.toHexString(rgba8888))
            while (builder.length < 8) builder.insert(0, "0")
            return builder.toString()
        }

    companion object {
        const val RED_MASK = -0x1_00_00_00
        const val GREEN_MASK = 0x00_FF_00_00
        const val BLUE_MASK = 0x00_00_FF_00
        const val RGB_MASK = RED_MASK or GREEN_MASK or BLUE_MASK
        const val ALPHA_MASK = 0x00_00_00_FF
        const val Empty = 0x00_00_00_00
        private val Int.to255 get() = if (this < 0) this + 255 else this
        fun blend(bk: Pixel, fg: Pixel): Pixel {
            val fga = fg.af
            val r = fg.rf * fga + bk.rf * (1f - fga)
            val g = fg.gf * fga + bk.gf * (1f - fga)
            val b = fg.bf * fga + bk.bf * (1f - fga)
            return Pixel(r, g, b, bk.af)
        }

        fun Color.toPixel() = Pixel(rgba8888())
    }
}
