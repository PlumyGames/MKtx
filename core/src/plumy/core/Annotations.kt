package plumy.core

import mindustry.Vars
import java.lang.annotation.Inherited

/**
 * It indicates this property/field should be serialized into save or a datapack.
 */
@Retention(AnnotationRetention.SOURCE)
@Inherited
@MustBeDocumented
@Target(AnnotationTarget.PROPERTY, AnnotationTarget.FIELD)
annotation class Serialized
/**
 * It indicates this parameter is used to output something.
 * ```kotlin
 * fun getFace(@Out v:Vec2)
 * ```
 */
@Retention(AnnotationRetention.SOURCE)
@Target(AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.FUNCTION)
@MustBeDocumented
annotation class Out

/**
 * It indicates this should be called or accessed only when [Vars.headless] is false.
 * ## Use case
 * 1. On a property or field, you shouldn't access them, it may provide wrong data or even crash the game.
 * 2. On a function, you shouldn't call them, it could crash the game.
 * 3. On a class or object, the class loader mustn't load them, probably because static initialization could crash the game.
 */
@Retention(AnnotationRetention.SOURCE)
@MustBeDocumented
annotation class ClientOnly

/**
 * It indicates this should be called or accessed only when [Vars.headless] is true.
 * ## Use case
 * 1. On a property or field, you shouldn't access them, it may provide wrong data or even crash the game.
 * 2. On a function, you shouldn't call them, it could crash the game.
 * 3. On a class or object, the class loader mustn't load them, probably because static initialization could crash the game.
 */
@Retention(AnnotationRetention.SOURCE)
@Inherited
@MustBeDocumented
annotation class HeadlessOnly