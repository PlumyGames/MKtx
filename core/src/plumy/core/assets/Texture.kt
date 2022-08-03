package plumy.core.assets

import arc.graphics.g2d.TextureRegion

typealias TR = TextureRegion
typealias TRs = Array<TextureRegion>
/**
 * Use this to avoid `lateinit var`
 */
val EmptyTR = TR()
/**
 * Use this to avoid `lateinit var`
 */
val EmptyTRs = emptyArray<TR>()