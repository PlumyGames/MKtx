package plumy.texture

import arc.graphics.Pixmap

/**
 * The pixel from raw texture which corresponds a black pixel on mask will be drawn.
 * @param onBlack If true, only draw on black pixel on mask. Otherwise, only draw on non-black pixel.
 */
class AndTextureMask(
    val mask: ITexture,
    val onBlack: Boolean = true,
) : IMask {
    override fun draw(base: ITexture, x: Int, y: Int, color: Int) {
        val c = Pixel(mask[x, y])
        if (onBlack) {
            if (c.isBlack)
                base[x, y] = color
        } else {
            if (!c.isBlack)
                base[x, y] = color
        }
    }

    override fun draw(base: Pixmap, x: Int, y: Int, color: Int) {
        val c = Pixel(mask[x, y])
        if (onBlack) {
            if (c.isBlack)
                base[x, y] = color
        } else {
            if (!c.isBlack)
                base[x, y] = color
        }
    }
    /**
     * Create a new [AndTextureMask] which has reversed [onBlack] against this.
     * @return a new [AndTextureMask] object
     */
    fun reversed() = AndTextureMask(mask, !onBlack)
}

interface IMask {
    fun draw(base: ITexture, x: Int, y: Int, color: Int)
    fun draw(base: Pixmap, x: Int, y: Int, color: Int)
}
