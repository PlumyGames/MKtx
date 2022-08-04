package plumy.texture

import arc.graphics.Color
import arc.graphics.Pixmap
import plumy.texture.Pixel.Companion.blend
import plumy.texture.Pixel.Companion.toPixel

class PlainLayerProcessor : ILayerProcessor {
    override fun process(original: ITexture): ITexture = original
}

class TintLayerProcessor(
    val color: Color,
) : ILayerProcessor {
    val dye = color.toPixel()
    override fun process(original: ITexture): ITexture {
        val raw = original.pixels
        val width = raw.width
        val height = raw.height
        val res = Pixmap(width, height)
        for (x in 0 until width) {
            for (y in 0 until height) {
                val c = Pixel(raw[x, y])
                if (c.isVisible) {
                    res[x, y] = blend(c, dye)
                }
            }
        }
        return ProcessedTexture(res)
    }
}


class TintLerpLayerProcessor(
    val color: Color,
) : ILayerProcessor {
    val dye = color.toPixel()
    override fun process(original: ITexture): ITexture {
        val raw = original.pixels
        val width = raw.width
        val height = raw.height
        val res = Pixmap(width, height)
        val cur = Color()
        for (x in 0 until width) {
            for (y in 0 until height) {
                cur.rgba8888(raw[x, y])
                if (cur.a != 0f) {
                    cur.lerp(cur.r, cur.g, cur.b, 1f, color.a)
                    res[x, y] = cur.rgba8888()
                }
            }
        }
        return ProcessedTexture(res)
    }
}