package plumy.animation

import arc.util.Time
import mindustry.gen.Building
import plumy.animation.SharedAnimationBehaviors.linerLoopByTime
import plumy.core.arc.Tick
import plumy.core.assets.EmptyTRs
import plumy.core.assets.TR
import plumy.core.assets.TRs
import plumy.core.progress

typealias SharedAnimationBehavior = (all: TRs, duration: Tick) -> TR

/**
 * An animation independent of each [Building].
 * Therefore, the progress is based on [Time.time].
 */
class SharedAnimation(
    val allFrames: TRs,
    val duration: Tick,
    val behavior: SharedAnimationBehavior = linerLoopByTime,
) : IFramed {
    override val curFrame: TR get() = behavior(allFrames, duration)

    companion object {
        val Empty = SharedAnimation(EmptyTRs, 0f)
    }
}

object SharedAnimationBehaviors {
    val linerLoopByTime: SharedAnimationBehavior = { all, duration ->
        all.progress(Time.time % duration / duration)
    }
}
