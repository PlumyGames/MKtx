package plumy.texture

import arc.graphics.Pixmap
import arc.graphics.Texture
import arc.graphics.g2d.TextureRegion
import java.io.Closeable

interface ITexture : Closeable {
    val pixels: Pixmap
    val disposable: Boolean
        get() = false

    override fun close() {
        if (disposable) pixels.dispose()
    }
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

fun IBakedModel.toTexture() = Texture(texture.pixels)
fun IBakedModel.toTextureRegion() = TextureRegion(toTexture())