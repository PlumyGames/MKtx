package plumy.texture

import arc.Core
import arc.files.Fi
import arc.graphics.Pixmap
import arc.graphics.g2d.PixmapRegion
import java.io.File

fun PixmapModelLayerForm(fi: Fi) = RawPixmapModelLayer(fi.toPixmap())
fun PixmapModelLayerForm(file: File) = RawPixmapModelLayer(file.toPixmap())
class RawPixmapModelLayer(pixmap: Pixmap) : IModelLayer {
    val processors = ArrayList<ILayerProcessor>()
    override val texture: ITexture = RawTexture(pixmap)
    override fun addProcess(processor: ILayerProcessor) {
        processors.add(processor)
    }

    override fun process(): ITexture {
        var cur = texture
        for (processor in processors) {
            val res = processor.process(cur)
            cur = res
        }
        return cur
    }
}

fun PixmapRegionModelLayerFrom(name: String) = PixmapRegionModelLayer(Core.atlas.getPixmap(name))
class PixmapRegionModelLayer(val region: PixmapRegion) : IModelLayer {
    override val texture: ITexture = PixmapRegionTexture(region)
    val processors = ArrayList<ILayerProcessor>()
    override fun addProcess(processor: ILayerProcessor) {
        processors.add(processor)
    }

    override fun process(): ITexture {
        var cur = texture
        for (processor in processors) {
            val res = processor.process(cur)
            cur = res
        }
        return cur
    }
}