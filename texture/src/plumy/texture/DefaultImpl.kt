package plumy.texture

import arc.graphics.Color
import plumy.texture.Pixel.Companion.toPixel
import kotlin.math.min

class StackIconBakery(
    val width: Int,
    val height: Int,
) : IBakery {
    override fun bake(layers: List<ILayer>): LayerBuffer {
        val res = LayerBuffer(width, height)
        for (layer in layers) {
            layer.process().let {
                res.coverBy(it)
            }
        }
        return res
    }
}

fun LayerBuffer.coverBy(cover: LayerBuffer) {
    val width = min(this.width, cover.width)
    val height = min(this.height, cover.height)
    val baseT = Color()
    val coverT = Color()
    for (x in 0 until width) {
        for (y in 0 until height) {
            baseT.rgba8888(this[x, y])
            coverT.rgba8888(cover[x, y])
            this[x, y] = baseT.lerp(coverT.r, coverT.g, coverT.b, 1f, coverT.a).rgba8888()
        }
    }
}

class MonochromeLayerProcessor(
    val redProportion: Float = 0.2125f,
    val greenProportion: Float = 0.7154f,
    val blueProportion: Float = 0.0721f,
) : ILayerProcessor {
    override fun process(original: LayerBuffer): LayerBuffer {
        val width = original.width
        val height = original.height
        val cur = Color()
        for (x in 0 until width) {
            for (y in 0 until height) {
                cur.rgba8888(original[x, y]).apply {
                    val mono = r * redProportion + g * greenProportion + b * blueProportion
                    r = mono
                    g = mono
                    b = mono
                }
                original[x, y] = cur.rgba8888()
            }
        }
        return original
    }
}

class TintBlendLayerProcessor(
    val dye: Pixel,
) : ILayerProcessor {
    constructor(color: Color) : this(color.toPixel())

    override fun process(original: LayerBuffer): LayerBuffer {
        val width = original.width
        val height = original.height
        for (x in 0 until width) {
            for (y in 0 until height) {
                val c = Pixel(original[x, y])
                if (c.isVisible) {
                    original[x, y] = Pixel.blend(c, dye).rgba8888
                }
            }
        }
        return original
    }
}

class TintLerpLayerProcessor(
    val color: Color,
    val progress: Float,
) : ILayerProcessor {
    override fun process(original: LayerBuffer): LayerBuffer {
        val width = original.width
        val height = original.height
        val cur = Color()
        for (x in 0 until width) {
            for (y in 0 until height) {
                cur.rgba8888(original[x, y])
                if (cur.a != 0f) {
                    cur.lerp(color, progress)
                    original[x, y] = cur.rgba8888()
                }
            }
        }
        return original
    }
}

class MaskLayerProcessor(
    val mask: IMask,
) : ILayerProcessor {
    override fun process(original: LayerBuffer): LayerBuffer {
        val width = original.width
        val height = original.height
        val res = original.copy()
        for (x in 0 until width) {
            for (y in 0 until height) {
                mask.draw(on = res, x, y, color = original[x, y])
            }
        }
        return res
    }
}

class OffsetLayerProcessor(
    val x: Int,
    val y: Int,
) : ILayerProcessor {
    override fun process(original: LayerBuffer): LayerBuffer {
        TODO("Not yet implemented")
    }
}