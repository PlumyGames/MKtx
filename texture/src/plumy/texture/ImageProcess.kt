package plumy.texture

import arc.graphics.Color

fun ITexture.coverBy(cover: ITexture) {
    val width = this.width
    val height = this.height
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
