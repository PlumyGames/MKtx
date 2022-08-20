package plumy.core.arc

import arc.struct.IntSeq

inline fun IntSeq.forEach(func: (Int) -> Unit) {
    for (i in 0 until size) {
        func(this[i])
    }
}
/**
 * @param predicate whether to keep this integer
 */
inline fun IntSeq.retain(predicate: (Int) -> Boolean) {
    for (i in size - 1 downTo 0) {
        if (!predicate(this[i])) {
            removeIndex(i)
        }
    }
}
/**
 * @param func (Index,Element)
 */
inline fun IntSeq.forEachIndexed(func: (Int, Int) -> Unit) {
    for (i in 0 until size) {
        func(i, this[i])
    }
}
