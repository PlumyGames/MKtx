package plumy.texture

import arc.files.Fi
import arc.graphics.Pixmap
import java.io.File
import java.io.InputStream

/**
 * Read PNG, JPEG or BMP from [this].
 * It will allocate a new [Pixmap] object.
 * @see [Pixmap.dispose]
 */
fun File.readAsPixmap() = Pixmap(Fi(this))
/**
 * Read PNG, JPEG or BMP from [this].
 * It will allocate a new [Pixmap] object.
 * @see [Pixmap.dispose]
 */
fun Fi.readAsPixmap() = Pixmap(this)
/**
 * Read PNG, JPEG or BMP from [this].
 * It will allocate a new [Pixmap] object.
 * @see [Pixmap.dispose]
 */
fun InputStream.readAsPixmap() = Pixmap(this.readBytes())
