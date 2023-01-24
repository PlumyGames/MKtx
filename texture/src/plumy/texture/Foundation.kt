package plumy.texture

import arc.graphics.Pixmap
import arc.graphics.g2d.PixmapRegion
import kotlin.math.min

interface ILayerView {
    val width: Int
    val height: Int
    operator fun get(x: Int, y: Int): Int
    operator fun set(x: Int, y: Int, rgba8888: Int)
    fun recreateBuffer(): LayerBuffer {
        val buffer = LayerBuffer(width, height)
        for (x in 0 until width) {
            for (y in 0 until height) {
                buffer[x, y] = this[x, y]
            }
        }
        return buffer
    }
}

class LayerBuffer private constructor(
    override val width: Int,
    override val height: Int,
    val pixels: IntArray,
) : ILayerView {
    constructor(
        maxX: Int, maxY: Int
    ) : this(maxX, maxY, IntArray(maxX * maxY))

    override operator fun get(x: Int, y: Int): Int = pixels[x + y * width]
    override operator fun set(x: Int, y: Int, rgba8888: Int) {
        pixels[x + y * width] = rgba8888
    }

    override fun recreateBuffer() = LayerBuffer(width, height, pixels.copyOf())
}

class PixmapLayerViewWrapper(val pixmap: Pixmap) : ILayerView {
    override val width: Int = pixmap.width
    override val height: Int = pixmap.height
    override fun get(x: Int, y: Int): Int = pixmap[x, y]

    override fun set(x: Int, y: Int, rgba8888: Int) {
        pixmap[x, y] = rgba8888
    }
}

fun Pixmap.toLayerView() = PixmapLayerViewWrapper(this)

class PixmapRegionLayerViewWrapper(val pixmap: PixmapRegion) : ILayerView {
    override val width: Int = pixmap.width
    override val height: Int = pixmap.height
    override fun get(x: Int, y: Int): Int = pixmap[x, y]

    override fun set(x: Int, y: Int, rgba8888: Int) {
        throw NotImplementedError("${PixmapRegion::class.simpleName} can't be set.")
    }
}

fun PixmapRegion.toLayerView() = PixmapRegionLayerViewWrapper(this)

/**
 * It will create a [Pixmap] whose lifecycle should be handled by users.
 */
fun LayerBuffer.createPixmap(): Pixmap {
    val pixmap = Pixmap(width, height)
    for (x in 0 until width) {
        for (y in 0 until height) {
            pixmap[x, y] = this[x, y]
        }
    }
    return pixmap
}

/**
 * Safely draw [this] on [pixmap] without [IndexOutOfBoundsException].
 */
fun LayerBuffer.drawOnPixmap(pixmap: Pixmap): Pixmap {
    val width = min(this.width, pixmap.width)
    val height = min(this.height, pixmap.height)
    for (x in 0 until width) {
        for (y in 0 until height) {
            pixmap[x, y] = this[x, y]
        }
    }
    return pixmap
}

fun Pixmap.toLayerBuffer(): LayerBuffer {
    val buffer = LayerBuffer(width, height)
    for (x in 0 until width) {
        for (y in 0 until height) {
            buffer[x, y] = this[x, y]
        }
    }
    return buffer
}

fun PixmapRegion.toLayerBuffer(): LayerBuffer {
    val buffer = LayerBuffer(width, height)
    for (x in 0 until width) {
        for (y in 0 until height) {
            buffer[x, y] = this[x, y]
        }
    }
    return buffer
}


interface ILayer {
    fun process(): ILayerView
}

interface ILayerProcessor {
    /**
     * Process [original] layer.
     * @return the processed result. It can be the same object as [original].
     */
    fun process(original: ILayerView): ILayerView
}

interface IBakery {
    fun bake(layers: List<ILayer>): LayerBuffer
}

fun IBakery.bake(vararg layers: ILayer) = bake(layers.toList())

/**
 * Default implementation of [ILayer].
 * - Supports [ILayerProcessor].
 */
class Layer(val raw: LayerBuffer) : ILayer {
    constructor(raw: LayerBuffer, config: LayerSpec.() -> Unit) : this(raw) {
        LayerSpec(this).config()
    }

    val processors = ArrayList<ILayerProcessor>()
    fun addProcess(processor: ILayerProcessor) {
        processors.add(processor)
    }

    fun removeProcess(processor: ILayerProcessor) {
        processors.remove(processor)
    }

    override fun process(): ILayerView {
        var cur: ILayerView = raw
        for (processor in processors) {
            cur = processor.process(cur)
        }
        return cur
    }
}

operator fun Layer.plusAssign(processor: ILayerProcessor) {
    this.addProcess(processor)
}

operator fun Layer.plus(processor: ILayerProcessor): Layer {
    this.addProcess(processor)
    return this
}

inline operator fun Layer.invoke(config: LayerSpec.() -> Unit): Layer {
    LayerSpec(this).config()
    return this
}
@JvmInline
value class LayerSpec(
    val layer: Layer,
) {
    operator fun ILayerProcessor.unaryPlus() {
        layer.addProcess(this)
    }

    operator fun ILayerProcessor.unaryMinus() {
        layer.removeProcess(this)
    }
}