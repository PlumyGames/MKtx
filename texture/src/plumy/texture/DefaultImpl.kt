package plumy.texture

import arc.graphics.Color
import plumy.texture.Pixel.Companion.toPixel
import java.util.*
import kotlin.math.min

class StackIconBakery(
    val width: Int,
    val height: Int,
) : IBakery {
    val postProcessor = mutableListOf<ILayerProcessor>()
    override fun bake(layers: List<ILayer>): LayerBuffer {
        val merged = LayerBuffer(width, height)
        for (layer in layers) {
            layer.process().let {
                merged.coverBy(it)
            }
        }
        var res: ILayerView = merged
        for (processor in postProcessor) {
            res = processor.process(merged)
        }
        return if (res is LayerBuffer) res else res.recreateBuffer()
    }
}

fun ILayerView.coverBy(cover: ILayerView) {
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
    override fun process(original: ILayerView): ILayerView {
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

    override fun process(original: ILayerView): ILayerView {
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
    override fun process(original: ILayerView): ILayerView {
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
    override fun process(original: ILayerView): ILayerView {
        val width = original.width
        val height = original.height
        val res = original.recreateBuffer()
        for (x in 0 until width) {
            for (y in 0 until height) {
                mask.draw(on = res, x, y, color = original[x, y])
            }
        }
        return res
    }
}

class OffsetLayerProcessor(
    val dx: Int,
    val dy: Int,
) : ILayerProcessor {
    override fun process(original: ILayerView): ILayerView {
        val width = original.width
        val height = original.height
        val res = original.recreateBuffer()
        for (x in 0 until width) {
            for (y in 0 until height) {
                val targetX = x + dx
                val targetY = x + dy
                if (targetX in 0 until res.width && targetY in 0 until res.height) {
                    res[targetX, targetY] = original[x, x]
                }
            }
        }
        return res
    }
}

/**
 * Apply anti-aliasing on layer.
 * This algorithm comes from [Mindustry tools](https://github.com/Anuken/Mindustry/blob/f46a9730a3b637602ee706c1d04f23185630860b/tools/build.gradle#L31).
 */
object AntiAliasingLayerProcessor : ILayerProcessor {
    override fun process(original: ILayerView): ILayerView {
        return antiAliasing(original)
    }
}

@Suppress("LocalVariableName")
internal fun antiAliasing(from: ILayerView): LayerBuffer {
    val out = LayerBuffer(from.width, from.height)
    val color = Color()
    val sum = Color()
    val suma = Color()
    val p = IntArray(9)
    fun ILayerView.getRGB(ix: Int, iy: Int): Int =
        this[ix.coerceIn(0, width - 1), iy.coerceIn(0, height - 1)]
    for (x in 0 until from.width) {
        for (y in 0 until from.height) {
            val A: Int = from.getRGB(x - 1, y + 1)
            val B: Int = from.getRGB(x, y + 1)
            val C: Int = from.getRGB(x + 1, y + 1)
            val D: Int = from.getRGB(x - 1, y)
            val E: Int = from.getRGB(x, y)
            val F: Int = from.getRGB(x + 1, y)
            val G: Int = from.getRGB(x - 1, y - 1)
            val H: Int = from.getRGB(x, y - 1)
            val I: Int = from.getRGB(x + 1, y - 1)
            Arrays.fill(p, E)
            if (D == B && D != H && B != F) p[0] = D
            if ((D == B && D != H && B != F && E != C) || (B == F && B != D && F != H && E != A)) p[1] = B
            if (B == F && B != D && F != H) p[2] = F
            if ((H == D && H != F && D != B && E != A) || (D == B && D != H && B != F && E != G)) p[3] = D
            if ((B == F && B != D && F != H && E != I) || (F == H && F != B && H != D && E != C)) p[5] = F
            if (H == D && H != F && D != B) p[6] = D
            if ((F == H && F != B && H != D && E != G) || (H == D && H != F && D != B && E != I)) p[7] = H
            if (F == H && F != B && H != D) p[8] = F
            suma.set(0)

            for (c in p) {
                color.rgba8888(c)
                color.premultiplyAlpha()
                suma.r(suma.r + color.r)
                suma.g(suma.g + color.g)
                suma.b(suma.b + color.b)
                suma.a(suma.a + color.a)
            }
            var fm = if (suma.a <= 0.001f) 0f else (1f / suma.a)
            suma.mul(fm, fm, fm, fm)
            var total = 0f
            sum.set(0)

            for (c in p) {
                color.rgba8888(c)
                val a = color.a
                color.lerp(suma, (1f - a))
                sum.r(sum.r + color.r)
                sum.g(sum.g + color.g)
                sum.b(sum.b + color.b)
                sum.a(sum.a + a)
                total += 1f
            }
            fm = 1f / total
            sum.mul(fm, fm, fm, fm)
            out[x, y] = sum.rgba8888()
            sum.set(0)
        }
    }
    return out
}