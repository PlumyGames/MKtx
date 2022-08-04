package plumy.texture

import arc.graphics.Pixmap

class Icon(override val texture: ITexture) : IBakedModel
class StackIconMaker(
    val width: Int,
    val height: Int,
) : IBakery {
    override fun bake(layers: List<IModelLayer>): IBakedModel {
        val res = Pixmap(width, height)
        for (layer in layers) {
            layer.process().use {
                res.coverBy(it)
            }
        }
        return Icon(BakedTexture(res))
    }
}
