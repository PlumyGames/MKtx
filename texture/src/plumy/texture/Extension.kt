package plumy.texture

import arc.graphics.Pixmap
import arc.graphics.Texture
import arc.graphics.g2d.TextureRegion

fun IBakedModel.toTexture() = Texture(texture.toPixmap())
fun IBakedModel.toTextureRegion() = TextureRegion(toTexture())
operator fun ITexture.set(x: Int, y: Int, pixel: Pixel) {
    this[x, y] = pixel.rgba8888
}

operator fun Pixmap.set(x: Int, y: Int, pixel: Pixel) {
    this[x, y] = pixel.rgba8888
}

inline operator fun IModelLayer.invoke(config: ModelLayerSpec.() -> Unit): IModelLayer {
    ModelLayerSpec(this).config()
    return this
}
@JvmInline
value class ModelLayerSpec(
    val modelLayer: IModelLayer,
) {
    operator fun ILayerProcessor.unaryPlus() {
        modelLayer.addProcess(this)
    }

    operator fun ILayerProcessor.unaryMinus() {
        modelLayer.removeProcess(this)
    }
}