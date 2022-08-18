package plumy.dsl

import mindustry.ctype.UnlockableContent
import mindustry.type.*
import kotlin.math.absoluteValue

// Item
operator fun Item.plus(amount: Int) =
    ItemStack(this, amount)

operator fun ItemStack.plusAssign(amount: Int) {
    this.amount += amount.absoluteValue
}

operator fun ItemStack.minusAssign(amount: Int) {
    this.amount -= amount.absoluteValue
}

// Fluid
operator fun Liquid.plus(amount: Float) =
    LiquidStack(this, amount)

operator fun LiquidStack.plusAssign(amount: Float) {
    this.amount += amount.absoluteValue
}

operator fun LiquidStack.minusAssign(amount: Float) {
    this.amount -= amount.absoluteValue
}

// Payload
operator fun UnlockableContent.plus(amount: Int) =
    PayloadStack(this, amount)

operator fun PayloadStack.plusAssign(amount: Int) {
    this.amount += amount.absoluteValue
}

operator fun PayloadStack.minusAssign(amount: Int) {
    this.amount -= amount.absoluteValue
}
