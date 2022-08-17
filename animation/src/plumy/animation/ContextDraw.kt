@file:JvmName("DrawT")

package plumy.animation

import arc.graphics.Color
import arc.graphics.g2d.Draw
import mindustry.gen.Building
import plumy.animation.ContextDraw.Draw
import plumy.core.assets.TR

/**
 * ContextDraw let you change the [Draw]'s behavior globally.
 */
object ContextDraw {
    var ALPHA: Float = 1f
        set(value) {
            field = value.coerceIn(0f, 1f)
        }

    fun RESET_CONTEXT() {
        ALPHA = 1f
    }

    fun SetColor(color: Color) {
        Draw.color(color)
    }

    fun SetAlpha(alpha: Float) {
        Draw.alpha(alpha)
    }

    fun AddAlpha(alpha: Float) {
        Draw.alpha(Draw.getColor().a * alpha)
    }

    fun TR.DrawScale(
        x: Float, y: Float, scale: Float,
        rotation: Float = 0f,
    ) {
        Draw.alpha(Draw.getColor().a * ALPHA)
        Draw.rect(
            this, x, y,
            width * Draw.scl * Draw.xscl * scale,
            height * Draw.scl * Draw.yscl * scale,
            rotation
        )
    }

    fun TR.DrawOn(build: Building, rotation: Float = 0f) {
        this.Draw(build.x, build.y, rotation)
    }

    fun TR.Draw(
        x: Float, y: Float, rotation: Float = 0f,
    ) {
        Draw.alpha(Draw.getColor().a * ALPHA)
        Draw.rect(this, x, y, rotation)
    }

    fun TR.DrawSize(
        x: Float, y: Float,
        width: Float = this.width * Draw.scl * Draw.xscl,
        height: Float = this.height * Draw.scl * Draw.yscl,
        rotation: Float = 0f,
    ) {
        Draw.alpha(Draw.getColor().a * ALPHA)
        Draw.rect(this, x, y, width, height, rotation)
    }

    fun ResetColor() {
        Draw.color()
    }

    fun ResetDraw() {
        Draw.reset()
    }
}
