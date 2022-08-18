package plumy.dsl

import arc.Core
import kotlin.properties.PropertyDelegateProvider
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty


inline fun <Owner, reified T> Setting(
    key: String,
    default: T,
    noinline onProvided: Setting<Owner, T>.(String) -> Unit = {},
) = Setting(key, default, T::class.java, onProvided)

class Setting<Owner, T>(
    val key: String,
    val default: T,
    val clz: Class<T>,
    val onProvided: Setting<Owner, T>.(String) -> Unit = {},
) : ReadWriteProperty<Owner, T>, PropertyDelegateProvider<Owner, Setting<Owner, T>> {
    override fun provideDelegate(thisRef: Owner, property: KProperty<*>): Setting<Owner, T> {
        onProvided(property.name)
        return this
    }
    @Suppress("UNCHECKED_CAST")
    override fun getValue(thisRef: Owner, property: KProperty<*>): T {
        val v = Core.settings.get(property.name, default)
        return if (clz.isInstance(v)) v as T
        else default
    }
    @Suppress("UNCHECKED_CAST")
    operator fun get(key: String): T {
        val v = Core.settings.get(key, default)
        return if (clz.isInstance(v)) v as T
        else default
    }

    override fun setValue(thisRef: Owner, property: KProperty<*>, value: T) {
        Core.settings.put(property.name, value)
    }

    operator fun set(key: String, value: T) {
        Core.settings.put(key, value)
    }
}