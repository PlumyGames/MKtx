package plumy.core


fun <T> Array<T>.progress(progress: Float): T {
    val p = progress.coerceIn(0f, 1f)
    val index = (p * size).toInt().coerceAtMost(size - 1)
    return this[index]
}