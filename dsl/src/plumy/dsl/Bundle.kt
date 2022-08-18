package plumy.dsl

import arc.Core
import arc.util.I18NBundle

typealias BundleKey = String

/**
 * Format i18n key in the [Core.atlas]
 */
fun BundleKey.bundle(vararg args: Any): String =
    Core.bundle.format(this, *args)
/**
 * Format i18n key in the [bundles]
 */
fun BundleKey.bundle(bundles: I18NBundle, vararg args: Any): String =
    bundles.format(this, *args)
/**
 * Find i18n key in the [Core.atlas]
 */
val BundleKey.bundle: String
    get() = Core.bundle.format(this)
