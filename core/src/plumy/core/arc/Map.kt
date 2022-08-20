package plumy.core.arc

import arc.struct.IntMap
import arc.struct.ObjectMap

operator fun <K, V> ObjectMap<K, V>.set(key: K, value: V) {
    this.put(key, value)
}

operator fun <T> IntMap<T>.set(key: Int, value: T) {
    put(key, value)
}