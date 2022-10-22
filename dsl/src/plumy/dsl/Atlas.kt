package plumy.dsl

import arc.Core
import arc.graphics.g2d.TextureRegion

val String.sprite: TextureRegion
    get() = Core.atlas.find(this)


infix fun TextureRegion.or(planB: TextureRegion): TextureRegion =
    if (this.found()) this
    else planB

inline infix fun TextureRegion.or(planB: () -> TextureRegion): TextureRegion =
    if (this.found()) this
    else planB()

