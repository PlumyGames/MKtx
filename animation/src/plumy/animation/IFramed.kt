package plumy.animation

import arc.graphics.Color
import plumy.animation.ContextDraw.Draw
import plumy.animation.ContextDraw.ResetDraw
import plumy.animation.ContextDraw.SetColor
import plumy.core.assets.TR

interface IFramed {
    val curFrame: TR
}

fun IFramed.draw(x: Float, y: Float, rotation: Float = 0f) {
    curFrame.Draw(x, y, rotation)
}

fun IFramed.draw(color: Color, x: Float, y: Float, rotation: Float = 0f) {
    SetColor(color)
    curFrame.Draw(x, y, rotation)
    ResetDraw()
}

inline fun IFramed.draw(draw: (TR) -> Unit) {
    draw(curFrame)
}