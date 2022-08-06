package plumy.texture

import arc.files.Fi
import arc.graphics.Pixmap
import arc.graphics.g2d.PixmapRegion
import java.io.File

open class PixmapDelegateTexture(val pixmap: Pixmap) : ITexture {
    override fun toPixmap() = pixmap
    override fun get(x: Int, y: Int): Int = pixmap[x, y]
    override fun set(x: Int, y: Int, color: Int) {
        pixmap[x, y] = color
    }

    override val width: Int = pixmap.width
    override val height: Int = pixmap.height
    override fun close() {
        if (disposable) pixmap.dispose()
    }
}

class BakedTexture(pixmap: Pixmap) : PixmapDelegateTexture(pixmap) {
    override val disposable = true
}

fun PixmapTextureFrom(fi: Fi) = RawTexture(fi.toPixmap())
fun PixmapTextureFrom(file: File) = RawTexture(file.toPixmap())
class RawTexture(pixmap: Pixmap) : PixmapDelegateTexture(pixmap)
class ProcessedTexture(pixmap: Pixmap) : PixmapDelegateTexture(pixmap) {
    override val disposable = true
}

class PixmapRegionTexture(val region: PixmapRegion) : ITexture {
    override fun get(x: Int, y: Int): Int {
        return region[x, y]
    }

    override fun set(x: Int, y: Int, color: Int) {
        throw UnsupportedOperationException()
    }

    override val width: Int
        get() = region.width
    override val height: Int
        get() = region.height

    override fun toPixmap(): Pixmap {
        return region.crop()
    }
}
