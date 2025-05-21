package me.juanescobar.appejercicios

import com.google.mlkit.vision.common.PointF3D
import kotlin.math.acos
import kotlin.math.hypot

fun calculateAngle(a: PointF3D, b: PointF3D, c: PointF3D): Double {
    val ab = Pair(a.x - b.x, a.y - b.y)
    val cb = Pair(c.x - b.x, c.y - b.y)
    val dot = ab.first * cb.first + ab.second * cb.second
    val magAB = hypot(ab.first.toDouble(), ab.second.toDouble())
    val magCB = hypot(cb.first.toDouble(), cb.second.toDouble())
    return Math.toDegrees(acos(dot / (magAB * magCB)))
}