package plumy.core.arc

import arc.struct.IntSeq
import arc.struct.Seq

/**
 * Only used in single thread.
 */
val tempIntSeq = IntSeq(64)
/**
 * Only used in single thread.
 * It uses [tempIntSeq] as temporary [IntSeq]
 */
inline fun IntSeq.snapshotForEach(func: (Int) -> Unit) {
    snapShot(tempIntSeq).run {
        for (i in 0 until size) {
            func(this[i])
        }
    }
}
/**
 * @return [temp]
 */
fun IntSeq.snapShot(temp: IntSeq): IntSeq {
    temp.copyFieldsFrom(this)
    return temp
}

fun IntSeq.copyFieldsFrom(other: IntSeq) {
    this.ordered = other.ordered
    if (size < other.size) {
        items = IntArray(other.size)
    }
    size = other.size
    System.arraycopy(other.items, 0, items, 0, size)
}

inline fun <T> Seq<T>.insertAfter(e: T, whenTrue: (T) -> Boolean) {
    for ((i, v) in this.withIndex()) {
        if (whenTrue(v)) {
            this.insert(i, e)
            return
        }
    }
}

inline fun <T> Seq<T>.insertBefore(e: T, whenTrue: (T) -> Boolean) {
    for ((i, v) in this.withIndex()) {
        if (whenTrue(v)) {
            this.insert(i - 1, e)
            return
        }
    }
}

fun <T> Seq<T>.shrinkTo(targetSize: Int) = apply {
    if (size > targetSize) {
        removeLastRange(size - targetSize)
    }
}
/**
 * It doesn't break the order if this [Seq] ordered.
 */
fun <T> Seq<T>.removeLast() = apply {
    check(size != 0) { "Array is empty." }
    this.items[size - 1] = null
    --size
}
/**
 * It doesn't break the order if this [Seq] ordered.
 */
fun <T> Seq<T>.removeLastRange(length: Int) = apply {
    if (length > size) throw IndexOutOfBoundsException("length can't be > size: $length > $size")
    if (length == size) this.clear()
    else {
        for (i in 0 until size - length) {
            this.items[size - 1] = null
            --size
        }
    }
}