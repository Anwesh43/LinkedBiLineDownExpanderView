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

val nodes : Int = 5
val lines : Int = 2
val parts : Int = 2
val scGap : Float = 0.02f
val strokeFactor : Float = 90f
val sizeFactor : Float = 2.9f
val colors : Array<String> = arrayOf("#3F51B5", "#F44336", "#4CAF50", "#03A9F4", "#009688")
val backColor : Int = Color.parseColor("#BDBDBD")
val delay : Long = 20
