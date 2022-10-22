package plumy.dsl

import mindustry.entities.Mover
import mindustry.gen.Bullet

inline fun Mover(crossinline move: Bullet.() -> Unit): Mover {
    return Mover {
        it.move()
    }
}


@Suppress("DuplicatedCode")
fun Bullet.copy(): Bullet {
    val d = Bullet.create()
    d.type = this.type
    d.owner = this.owner
    d.team = this.team
    d.x = this.x
    d.y = this.y
    d.aimX = this.aimX
    d.aimY = this.aimY
    d.aimTile = this.aimTile
    d.lifetime = this.lifetime
    d.time = this.time
    d.data = this.data
    d.drag = this.drag
    d.hitSize = this.hitSize
    d.damage = this.damage
    d.mover = this.mover
    d.fdata = this.fdata
    d.keepAlive = this.keepAlive
    d.originX = this.originX
    d.originY = this.originY
    d.lastX = this.lastX
    d.lastY = this.lastY
    d.hit = this.hit
    d.deltaX = this.deltaX
    d.deltaY = this.deltaY
    d.absorbed = this.absorbed
    //reset trail
    if (d.trail != null) {
        d.trail.clear()
    }
    d.vel.set(this.vel)
    d.add()
    return d
}
