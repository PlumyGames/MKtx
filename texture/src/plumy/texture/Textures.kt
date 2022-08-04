package plumy.texture

import arc.graphics.Pixmap
import arc.graphics.Pixmaps
import arc.graphics.TextureData
import arc.graphics.g2d.TextureRegion

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

class RawTexture(pixmap: Pixmap) : PixmapDelegateTexture(pixmap)
class ProcessedTexture(pixmap: Pixmap) : PixmapDelegateTexture(pixmap) {
    override val disposable = true
}

class AtlasRegionTexture(val region: TextureRegion) : ITexture {
    val data: TextureData = region.texture.textureData
    var pixmap: Pixmap? = null
    override fun init() {
        pixmap = data.pixmap
    }

    override fun get(x: Int, y: Int): Int {
        val pixmap = pixmap ?: throw UninitializedTextureException("$region in $this is not initialized.")
        return pixmap[x + region.x, y + region.y]
    }

    override fun set(x: Int, y: Int, color: Int) {
        val pixmap = pixmap ?: throw UninitializedTextureException("$region in $this is not initialized.")
        pixmap[x + region.x, y + region.y] = color
    }

    override val width: Int
        get() = region.width
    override val height: Int
        get() = region.height

    override fun toPixmap(): Pixmap {
        return Pixmaps.crop(region.texture.textureData.pixmap, region.x, region.y, region.width, region.height)
    }
}
