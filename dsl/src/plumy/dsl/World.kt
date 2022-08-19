package plumy.dsl

import arc.math.geom.Point2
import arc.math.geom.Position
import mindustry.Vars
import mindustry.core.World
import mindustry.gen.Building
import mindustry.gen.Buildingc
import mindustry.world.Block
import mindustry.world.Tile
import kotlin.math.roundToInt

typealias TileXY = Int
typealias TileXYs = Short
typealias TileXYf = Float
typealias TileXYd = Double
typealias WorldXY = Float
typealias PackedPos = Int
typealias UnpackedPos = Point2

/**
 * Try to get a building on this packed coordinate.
 */
inline fun <reified T> PackedPos.castBuild(): T? =
    Vars.world.build(this) as? T
/**
 * Try to get a building on this unpacked coordinate.
 */
inline fun <reified T> UnpackedPos.castBuild(): T? =
    Vars.world.build(x, y) as? T
/**
 * @see [Building.isValid]
 */
val Building?.exists: Boolean
    get() = this != null && this.isValid
/**
 * @see [Buildingc.isValid]
 */
val Buildingc?.exists: Boolean
    get() = this != null && this.isValid
/**
 * Unpack a packed coordinate.
 * @see [Point2.unpack]
 * @see [Building.pos]
 */
fun PackedPos.unpack(): UnpackedPos =
    Point2.unpack(this)
// tile
fun World.tile(x: Float, y: Float): Tile? = tile(x.toInt(), y.toInt())
fun World.tile(x: TileXYd, y: TileXYd): Tile? = tile(x.toInt(), y.toInt())
fun World.tile(x: TileXYs, y: TileXYs): Tile? = tile(x.toInt(), y.toInt())
// build
fun World.build(x: TileXYf, y: TileXYf): Building? = build(x.toInt(), y.toInt())
fun World.build(x: TileXYd, y: TileXYd): Building? = build(x.toInt(), y.toInt())
fun World.build(x: TileXYs, y: TileXYs): Tile? = tile(x.toInt(), y.toInt())
fun Tile.dstWorld(x: TileXY, y: TileXY): WorldXY =
    this.dst(x * Vars.tilesize.toFloat(), y * Vars.tilesize.toFloat())

fun Tile.dstWorld2(x: TileXY, y: TileXY): WorldXY =
    this.dst2(x * Vars.tilesize.toFloat(), y * Vars.tilesize.toFloat())
/**
 * Try to get a building on this packed coordinate.
 * @see [Point2.unpack]
 * @see [Building.pos]
 */
val PackedPos.build: Building?
    get() = Vars.world.build(this)
/**
 * Try to get a building on this coordinate.
 */
val UnpackedPos.build: Building?
    get() = Vars.world.build(x, y)
/**
 * Tile coordinate to world coordinate
 */
val TileXYs.worldXY: WorldXY
    get() = this.toFloat() * Vars.tilesize
/**
 * Tile coordinate to world coordinate
 */
val TileXY.worldXY: WorldXY
    get() = this.toFloat() * Vars.tilesize
/**
 * Tile coordinate to world coordinate
 */
val TileXYf.worldXY: WorldXY
    get() = this * Vars.tilesize
/**
 * Tile xy to world xy. Take block's offset into account
 */
fun Block.getCenterWorldXY(xy: TileXYs): WorldXY =
    offset + xy * Vars.tilesize
/**
 * Tile xy to world xy. Take block's offset into account
 */
fun Block.getCenterWorldXY(xy: TileXY): WorldXY =
    offset + xy * Vars.tilesize
/**
 * @see [World.tileWorld]
 */
val WorldXY.tileXY: TileXY
    get() = (this / Vars.tilesize).roundToInt()

fun Position.inTheWorld(): Boolean {
    if (x < -Vars.finalWorldBounds ||
        y < -Vars.finalWorldBounds
    ) return false
    if (x > Vars.world.tiles.height * Vars.tilesize + Vars.finalWorldBounds * 2 ||
        y > Vars.world.tiles.height * Vars.tilesize + Vars.finalWorldBounds
    ) return false
    return true
}