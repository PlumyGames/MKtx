package plumy.texture

import arc.graphics.Pixmap
import java.io.Closeable

interface ITexture : Closeable {
    operator fun get(x: Int, y: Int): Int
    operator fun set(x: Int, y: Int, color: Int)
    val width: Int
    val height: Int
    fun toPixmap(): Pixmap
    val disposable: Boolean
        get() = false

    override fun close() {}
}

interface IModelLayer {
    fun addProcess(processor: ILayerProcessor)
    fun process(): ITexture
    val texture: ITexture
}

operator fun IModelLayer.plusAssign(processor: ILayerProcessor) {
    this.addProcess(processor)
}

operator fun IModelLayer.plus(processor: ILayerProcessor): IModelLayer {
    this.addProcess(processor)
    return this
}

interface ILayerProcessor {
    fun process(original: ITexture): ITexture
}

interface IBakery {
    fun bake(layers: List<IModelLayer>): IBakedModel
}

fun IBakery.bake(vararg layers: IModelLayer) = bake(layers.toList())
interface IBakedModel {
    val texture: ITexture
}
