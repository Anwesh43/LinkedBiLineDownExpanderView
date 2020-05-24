package com.anwesh.uiprojects.bilinedownexpanderview

/**
 * Created by anweshmishra on 25/05/20.
 */

import android.view.View
import android.view.MotionEvent
import android.app.Activity
import android.content.Context
import android.graphics.Paint
import android.graphics.Canvas
import android.graphics.Color

val lines : Int = 2
val parts : Int = 2
val scGap : Float = 0.04f / (lines * parts)
val strokeFactor : Float = 90f
val sizeFactor : Float = 2.9f
val colors : Array<String> = arrayOf("#3F51B5", "#F44336", "#4CAF50", "#03A9F4", "#009688")
val backColor : Int = Color.parseColor("#BDBDBD")
val delay : Long = 20

fun Int.inverse() : Float = 1f / this
fun Float.maxScale(i : Int, n : Int) : Float = Math.max(0f, this - i * n.inverse())
fun Float.divideScale(i : Int, n : Int) : Float = Math.min(n.inverse(), maxScale(i, n)) * n
fun Float.sinify() : Float = Math.sin(this * Math.PI).toFloat()

fun Canvas.drawBiLineDownExpanderLine(i : Int, scale : Float, size : Float, paint : Paint) {
    val sf1 : Float = scale.divideScale(0, parts)
    val sf2 : Float = scale.divideScale(1, parts)
    val si : Float = 1f - 2 * i
    save()
    translate(0f, size * si * (1 - sf2))
    drawLine(0f, 0f, size * sf1 * si, 0f, paint)
    restore()
}

fun Canvas.drawBiLineDownExpanderLines(scale : Float, size : Float, paint : Paint) {
    for (i in 0..(lines - 1)) {
        val sf : Float = scale.sinify().divideScale(i, lines)
        drawBiLineDownExpanderLine(i, sf, size, paint)
    }
}

fun Canvas.drawBLDENode(i : Int, scale : Float, paint : Paint) {
    val w : Float = width.toFloat()
    val h : Float = height.toFloat()
    val size : Float = Math.min(w, h) / sizeFactor
    paint.color = Color.parseColor(colors[i])
    paint.strokeCap = Paint.Cap.ROUND
    paint.strokeWidth = Math.min(w, h) / strokeFactor
    save()
    translate(w / 2, h / 2)
    drawBiLineDownExpanderLines(scale, size, paint)
    restore()
}

class BiLineDownExpanderView(ctx : Context) : View(ctx) {

    override fun onDraw(canvas : Canvas) {

    }

    override fun onTouchEvent(event : MotionEvent) : Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {

            }
        }
        return true
    }

    data class State(var scale : Float = 0f, var dir : Float = 0f, var prevScale : Float = 0f) {

        fun update(cb : (Float) -> Unit) {
            scale += scGap * dir
            if (Math.abs(scale - prevScale) > 1) {
                scale = prevScale + dir
                dir = 0f
                prevScale = scale
                cb(prevScale)
            }
        }

        fun startUpdating(cb : () -> Unit) {
            if (dir == 0f) {
                dir = 1f - 2 * prevScale
                cb()
            }
        }
    }
}