package plumy.core.arc

typealias Tick = Float
typealias TickI = Int
typealias TickD = Double
typealias Second = Float
typealias Minute = Float

val Tick.second
    get() = this * 60f
val Tick.minute
    get() = this * 60f * 60f
val TickI.second
    get() = this * 60f
val TickI.minute
    get() = this * 60f * 60f
val TickD.second: Float
    get() = (this * 60f).toFloat()
val TickD.minute: Float
    get() = (this * 60f * 60f).toFloat()