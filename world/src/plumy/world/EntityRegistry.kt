package plumy.world

import arc.func.Prov
import arc.struct.ObjectMap
import mindustry.gen.EntityMapping
import mindustry.gen.Entityc

/**
 * For entity register.
 * Because Mindustry forbade mod's [ClassLoader] to access parent,
 * so this class won't be shared among different mods.
 */
object EntityRegistry {
    data class ProvEntry(val name: String, val prov: Prov<*>)

    val Clz2Entry = ObjectMap<Class<*>, ProvEntry>()
    val Clz2Id = ObjectMap<Class<*>, Int>()
    fun <T> register(clz: Class<T>, prov: Prov<T>) where T : Entityc {
        Clz2Entry.put(clz, ProvEntry(clz.name, prov))
        Clz2Id.put(clz, EntityMapping.register(clz.name, prov))
    }

    inline fun <reified T : Entityc> register(prov: Prov<T>) {
        register(T::class.java, prov)
    }

    operator fun <T : Entityc> set(clz: Class<T>, prov: Prov<T>) {
        register(clz, prov)
    }

    operator fun <T : Entityc> get(c: Class<T>): Int = Clz2Id[c]
    inline fun <reified T : Entityc> getIdOf(): Int = Clz2Id[T::class.java]
}
