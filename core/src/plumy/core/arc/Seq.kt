package plumy.core.arc

import arc.struct.Seq

// Ctor
inline fun <reified T> NewSeq(
    ordered: Boolean = true,
    capacity: Int = 16,
) = Seq<T>(ordered, capacity, T::class.java)

fun <T> Seq<T>.set(other: Iterable<T>) = apply {
    clear()
    for (e in other) this.add(e)
}

fun <T> Seq<T>.set(other: Collection<T>) = apply {
    clear()
    for (e in other) this.add(e)
}
// Empty check
fun <T> Seq<T>.isNotEmpty() = !isEmpty
// Iteration
/**
 * Using index instead of iterator.
 */
inline fun <T> Seq<T>.forLoop(
    func: (T) -> Unit,
) = apply {
    for (i in 0 until this.size) {
        func(this[i])
    }
}
// Transform
fun <T> Array<T>.toSeq() = Seq(this)
fun <T> Array<T>.toSeq(seq: Seq<T>) = seq.set(this)
inline fun <reified T> Iterable<T>.toSeq(
    ordered: Boolean = true,
    capacity: Int = 16,
) = NewSeq<T>(ordered, capacity).apply {
    addAll(this@toSeq)
}

inline fun <reified T> Collection<T>.toSeq(
    ordered: Boolean = true,
    capacity: Int = size,
) = NewSeq<T>(ordered, capacity).apply {
    addAll(this@toSeq)
}

inline fun <reified T> Iterable<T>.toSeq(
    seq: Seq<T>,
) = seq.apply {
    clear()
    addAll(this@toSeq)
}

inline fun <reified T> Collection<T>.toSeq(
    seq: Seq<T>,
) = seq.apply {
    clear()
    addAll(this@toSeq)
}
// Add and remove
/**
 * @return a new Seq with the [element]
 */
operator fun <T> Seq<T>.plus(element: T) = Seq(this).apply {
    add(element)
}
/**
 * add the [element] into the Seq.
 */
operator fun <T> Seq<T>.plusAssign(element: T) {
    add(element)
}
/**
 * @return a new Seq without the [element]
 */
operator fun <T> Seq<T>.minus(element: T) = Seq(this).apply {
    removeIf { it == element }
}
/**
 * Remove the [element] in the Seq.
 */
operator fun <T> Seq<T>.minusAssign(element: T) {
    removeIf { it == element }
}
/**
 * @param predicate whether to keep this element
 */
inline fun <T> Seq<T>.retain(predicate: (T) -> Boolean) {
    val it = iterator()
    while (it.hasNext()) {
        if (!predicate(it.next())) {
            it.remove()
        }
    }
}
/**
 * It calls the [Seq.iterator] function.
 * Note this can't work in multi-thread or nested calling.
 * @see [Seq.removeAll]
 */
inline fun <T> Seq<T>.removeIf(predicate: (T) -> Boolean): Seq<T> {
    val it = iterator()
    while (it.hasNext()) {
        if (predicate(it.next())) {
            it.remove()
        }
    }
    return this
}
// contains
fun <T> Seq<T>.containsAll(other: Collection<T>): Boolean {
    for (e in other)
        if (!this.contains(e))
            return false
    return true
}
// Equals
fun <T> Collection<T>.equalsNoOrder(other: Seq<T>): Boolean =
    if (this.size == other.size)
        if (this.isEmpty()) true
        else other.containsAll(this)
    else false

fun <T> Seq<T>.equalsNoOrder(other: Collection<T>): Boolean =
    if (this.size == other.size)
        if (this.isEmpty) true
        else this.containsAll(other)
    else false
