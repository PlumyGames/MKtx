package plumy.texture

import arc.graphics.Pixmap

/**
 * The pixel from raw texture which corresponds a black pixel on mask will be drawn.
 */
class AndTextureMask(
    val mask: ITexture,
) : IMask {
    override fun draw(base: ITexture, x: Int, y: Int, color: Int) {
        val c = Pixel(mask[x, y])
        if (c.isBlack)
            base[x, y] = color
    }

    override fun draw(base: Pixmap, x: Int, y: Int, color: Int) {
        val c = Pixel(mask[x, y])
        if (c.isBlack)
            base[x, y] = color
    }
}

interface IMask {
    fun draw(base: ITexture, x: Int, y: Int, color: Int)
    fun draw(base: Pixmap, x: Int, y: Int, color: Int)
}
