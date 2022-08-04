package plumy.texture

import arc.Core
import arc.files.Fi
import java.io.File

fun PixmapModelLayerForm(fi: Fi) = RawPixmapModelLayer(fi.toPixmap())
fun PixmapModelLayerForm(file: File) = RawPixmapModelLayer(file.toPixmap())
fun PixmapRegionModelLayerFrom(name: String) = PixmapRegionModelLayer(Core.atlas.getPixmap(name))
fun PixmapTextureFrom(fi: Fi) = RawTexture(fi.toPixmap())
fun PixmapTextureFrom(file: File) = RawTexture(file.toPixmap())
