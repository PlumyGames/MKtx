package plumy.texture

import arc.files.Fi
import arc.graphics.Pixmap
import java.io.File
import java.io.InputStream

fun File.toPixmap() = Pixmap(Fi(this))
fun Fi.toPixmap() = Pixmap(this)
fun InputStream.toPixmap() = Pixmap(this.readBytes())
fun Pixmap.to2D(): Array<IntArray> {
    val res = Array(height) {
        IntArray(width)
    }
    for (x in 0 until width) {
        for (y in 0 until height) {
            res[x][y] = this[x, y]
        }
    }
    return res
}