package plumy.dsl

import mindustry.gen.Healthc

var Healthc.lostHp: Float
    get() = maxHealth() - health()
    set(value) {
        health(maxHealth() - value)
    }
var Healthc.lostHpPct: Float
    get() = 1f - healthPct
    set(value) {
        health((1 - value.coerceIn(0f, 1f)) * maxHealth())
    }
var Healthc.healthPct: Float
    get() = (healthf()).coerceIn(0f, 1f)
    set(value) {
        health(value.coerceIn(0f, 1f) * maxHealth())
    }