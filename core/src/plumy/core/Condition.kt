package plumy.core

import arc.Core
import arc.Graphics
import mindustry.Vars
import mindustry.core.GameState

/**
 * Runs codes only when [Vars.headless] is false
 * @see [Vars.headless]
 */
inline fun ClientOnly(func: () -> Unit): Boolean {
    if (!Vars.headless) {
        func()
        return true
    }
    return false
}
/**
 * Runs codes only when [Vars.headless] is true
 * @see [Vars.headless]
 */
inline fun HeadlessOnly(func: () -> Unit): Boolean {
    if (Vars.headless) {
        func()
        return true
    }
    return false
}
/**
 * Runs codes only when [Vars.mobile] is true
 * @see [Vars.mobile]
 */
inline fun MobileOnly(func: () -> Unit): Boolean {
    if (Vars.mobile) {
        func()
        return true
    }
    return false
}
/**
 * Runs codes only when [Vars.mobile] is false
 * @see [Vars.mobile]
 */
inline fun DesktopOnly(func: () -> Unit): Boolean {
    if (!Vars.mobile) {
        func()
        return true
    }
    return false
}
/**
 * Runs codes only on the Portrait Mode
 * @see [Graphics.isPortrait]
 */
inline fun PortraitModeOnly(func: () -> Unit): Boolean {
    if (Core.graphics.isPortrait) {
        func()
        return true
    }
    return false
}
/**
 * Runs codes only on the Landscape Mode
 * @see [Graphics.isPortrait]
 */
inline fun LandscapeModeOnly(func: () -> Unit): Boolean {
    if (!Core.graphics.isPortrait) {
        func()
        return true
    }
    return false
}
/**
 * Runs codes only when [Vars.steam] is true
 * @see [Vars.steam]
 */
inline fun SteamOnly(func: () -> Unit): Boolean {
    if (Vars.steam) {
        func()
        return true
    }
    return false
}
/**
 * Runs codes only when [Vars.steam] is false
 * @see [Vars.steam]
 */
inline fun NonSteamOnly(func: () -> Unit): Boolean {
    if (!Vars.steam) {
        func()
        return true
    }
    return false
}
/**
 * If the receiver is false, run codes.
 */
inline infix fun Boolean.Else(func: () -> Unit) {
    if (!this) {
        func()
    }
}
/**
 * Runs codes only when the game is not paused.
 * @see [GameState.isPaused]
 */
inline fun WhenNotPaused(func: () -> Unit) {
    if (!Vars.state.isPaused) {
        func()
    }
}