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
                res.coverBy(it.pixels)
            }
        }
        return Icon(BakedTexture(res))
    }
}

fun Pixmap.coverBy(cover: Pixmap) {
    val width = this.width
    val height = this.height
    for (x in 0 until width) {
        for (y in 0 until height) {
            val c = Pixel(this[x, y])
            val t = Pixel(cover[x, y])
            val r = c.coverBy(t)
            this[x, y] = r
        }
    }
}