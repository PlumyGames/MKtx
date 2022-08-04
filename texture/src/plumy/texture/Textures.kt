package plumy.texture

import arc.graphics.Pixmap

class BakedTexture(override val pixels: Pixmap) : ITexture
class RawTexture(override val pixels: Pixmap) : ITexture
class ProcessedTexture(override val pixels: Pixmap) : ITexture {
    override val disposable = true
}