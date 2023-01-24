@file:Suppress("RemoveRedundantBackticks")

import arc.files.Fi
import arc.graphics.Pixmap
import mindustry.graphics.Pal
import org.junit.jupiter.api.Test
import plumy.texture.*
import java.io.File
import javax.imageio.ImageIO

class TestIconGenerator {
    val rootDir = File("test")
    val `base` = rootDir.resolve("base.png")
    val `patch` = rootDir.resolve("patch.png")
    val `mask` = rootDir.resolve("mask.png")
    fun `gen icon`(): Pixmap {
        val maker = StackIconBakery(32, 32)
        val baked = maker.bake(
            Layer(`base`.readAsPixmap().toLayerBuffer()),
            Layer(`patch`.readAsPixmap().toLayerBuffer()) {
                +TintLerpLayerProcessor(Pal.accent, progress = 0.5f)
                +MonochromeLayerProcessor()
                +MaskLayerProcessor(AndTextureMask(`mask`.readAsPixmap().toLayerBuffer()))
            }
        )
        return baked.createPixmap()
    }
    @Test
    fun `test gen icon`() {
        `gen icon`()
    }
    @Test
    fun `test output icon`() {
        val icon = `gen icon`()
        val output = File.createTempFile("test-generated-icon", ".png")
        Fi(output).writePng(icon)
        println("Generated at ${output.absolutePath}")
    }
    @Test
    fun `test read buffered image form local file`() {
        ImageIO.read(`base`)
    }
}


