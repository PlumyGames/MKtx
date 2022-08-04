package plumy.texture

import arc.graphics.Pixmap

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

fun ITexture.coverBy(cover: ITexture) {
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

fun Pixmap.coverBy(cover: ITexture) {
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
