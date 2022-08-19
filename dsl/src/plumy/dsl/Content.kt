package plumy.dsl

import mindustry.ctype.Content

val Content.ID: Int
    get() = this.id.toInt()
