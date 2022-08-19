package plumy.core.arc

import arc.func.*

// Cons
operator fun <T> Cons<T>.invoke(t: T) = this.get(t)
operator fun <T, E : Throwable> ConsT<T, E>.invoke(t: T) = this.get(t)
operator fun <A, B> Cons2<A, B>.invoke(a: A, b: B) = this.get(a, b)
operator fun <A, B, C> Cons3<A, B, C>.invoke(a: A, b: B, c: C) = this.get(a, b, c)
operator fun <A, B, C, D> Cons4<A, B, C, D>.invoke(a: A, b: B, c: C, d: D) = this.get(a, b, c, d)
// Bool
operator fun Boolc.invoke(b: Boolean) = this.get(b)
operator fun Boolp.invoke() = this.get()
operator fun <T> Boolf<T>.invoke(t: T) = this.get(t)
operator fun <A, B> Boolf2<A, B>.invoke(a: A, b: B) = this.get(a, b)
operator fun <A, B, C> Boolf3<A, B, C>.invoke(a: A, b: B, c: C) = this.get(a, b, c)
// Float
operator fun Floatc.invoke(f: Float) = this.get(f)
operator fun Floatp.invoke() = this.get()
operator fun Floatc2.invoke(x: Float, y: Float) = this.get(x, y)
operator fun Floatc4.invoke(x1: Float, y1: Float, x2: Float, y2: Float) = this.get(x1, y1, x2, y2)
operator fun <T> Floatf<T>.invoke(t: T) = this.get(t)
operator fun FloatFloatf.invoke(f: Float) = this.get(f)
// Prov
operator fun <T> Prov<T>.invoke(): T = this.get()
// Func
operator fun <P, R> Func<P, R>.invoke(p: P): R = this.get(p)
operator fun <P1, P2, R> Func2<P1, P2, R>.invoke(p1: P1, p2: P2): R = this.get(p1, p2)
operator fun <P1, P2, P3, R> Func3<P1, P2, P3, R>.invoke(p1: P1, p2: P2, p3: P3): R = this.get(p1, p2, p3)
// Int
operator fun Intc.invoke(i: Int) = this.get(i)
operator fun Intp.invoke() = this.get()
operator fun Intc2.invoke(x: Int, y: Int) = this.get(x, y)
operator fun Intc4.invoke(x1: Int, y1: Int, x2: Int, y2: Int) = this.get(x1, y1, x2, y2)
operator fun <T> Intf<T>.invoke(t: T) = this.get(t)
operator fun IntIntf.invoke(i: Int) = this.get(i)