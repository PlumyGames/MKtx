package plumy.texture

import arc.graphics.Color
import arc.graphics.Pixmap
import plumy.texture.Pixel.Companion.toPixel

class PlainLayerProcessor : ILayerProcessor {
    override fun process(original: ITexture): ITexture = original
}

class MonochromeLayerProcessor(
    val redProportion: Float = 0.2125f,
    val greenProportion: Float = 0.7154f,
    val blueProportion: Float = 0.0721f,
) : ILayerProcessor {
    override fun process(original: ITexture): ITexture {
        val width = original.width
        val height = original.height
        val res = Pixmap(width, height)
        val cur = Color()
        for (x in 0 until width) {
            for (y in 0 until height) {
                cur.rgba8888(original[x, y]).apply {
                    val mono = r * redProportion + g * greenProportion  + b * blueProportion
                    r = mono
                    g = mono
                    b = mono
                }
                res[x, y] = cur.rgba8888()
            }
        }
        return ProcessedTexture(res)
    }
}

class TintBlendLayerProcessor(
    val dye: Pixel,
) : ILayerProcessor {
    constructor(color: Color) : this(color.toPixel())

    override fun process(original: ITexture): ITexture {
        val width = original.width
        val height = original.height
        val res = Pixmap(width, height)
        for (x in 0 until width) {
            for (y in 0 until height) {
                val c = Pixel(original[x, y])
                if (c.isVisible) {
                    res[x, y] = Pixel.blend(c, dye)
                }
            }
        }
        return ProcessedTexture(res)
    }
}

class TintLerpLayerProcessor(
    val color: Color,
    val progress: Float,
) : ILayerProcessor {
    override fun process(original: ITexture): ITexture {
        val width = original.width
        val height = original.height
        val res = Pixmap(width, height)
        val cur = Color()
        for (x in 0 until width) {
            for (y in 0 until height) {
                cur.rgba8888(original[x, y])
                if (cur.a != 0f) {
                    cur.lerp(color, progress)
                    res[x, y] = cur.rgba8888()
                }
            }
        }
        return ProcessedTexture(res)
    }
}

class MaskLayerProcessor(
    val mask: IMask,
) : ILayerProcessor {
    override fun process(original: ITexture): ITexture {
        val width = original.width
        val height = original.height
        val res = Pixmap(width, height)
        for (x in 0 until width) {
            for (y in 0 until height) {
                mask.draw(base = res, x, y, original[x, y])
            }
        }
        return ProcessedTexture(res)
    }
}
