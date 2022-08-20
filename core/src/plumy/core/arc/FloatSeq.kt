package plumy.core.arc

import arc.struct.FloatSeq

/**
 * @param predicate whether to keep this float
 */
inline fun FloatSeq.retain(predicate: (Float) -> Boolean) {
    for (i in size - 1 downTo 0) {
        if (!predicate(this[i])) {
            removeIndex(i)
        }
    }
}