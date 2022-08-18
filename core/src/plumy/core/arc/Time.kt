package plumy.core.arc

import arc.util.Time

typealias Tick = Float

// Time to tick
val Float.second: Tick
    get() = this * 60f
val Float.minute: Tick
    get() = this * 60f * 60f
val Int.second: Tick
    get() = this * 60f
val Int.minute: Tick
    get() = this * 60f * 60f
val Double.second: Float
    get() = (this * 60f).toFloat()
val Double.minute: Float
    get() = (this * 60f * 60f).toFloat()
// Tick to second
val Tick.toSecondf: Float
    get() = this / Time.toSeconds
val Tick.toSecond: Int
    get() = (this / Time.toSeconds).toInt()