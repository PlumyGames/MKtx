package plumy.core.arc

import arc.struct.IntSet

/**
 * It calls the [IntSet.iterator] function. Note this can't work in multi-thread or nested calling.
 */
inline fun IntSet.forEach(func: (Int) -> Unit) {
    val it = this.iterator()
    while (it.hasNext) {
        func(it.next())
    }
}

/**
 * It calls the [IntSet.iterator] function. Note this can't work in multi-thread or nested calling.
 * @param func (Index,Element)
 */
inline fun IntSet.forEachIndexed(func: (Int, Int) -> Unit) {
    val it = this.iterator()
    var i = 0
    while (it.hasNext) {
        func(i, it.next())
        i++
    }
}
/**
 * It calls the [IntSet.iterator] function.
 * Note this can't work in multi-thread or nested calling.
 */
inline fun IntSet.removeAll(predicate: (Int) -> Boolean): IntSet {
    val it = this.iterator()
    while (it.hasNext) {
        if (predicate(it.next())) {
            it.remove()
        }
    }
    return this
}