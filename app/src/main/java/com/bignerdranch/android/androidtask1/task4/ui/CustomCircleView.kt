package com.bignerdranch.android.androidtask1.task4.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.View

class CustomCircleView(context: Context) : View(context) {

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val x = width / 2
        val y = height / 2
        val radius = width.coerceAtMost(height) / 4

        val paint = Paint().apply {
            style = Paint.Style.STROKE
            color = Color.parseColor("#FF4CAF50")
        }
            canvas.drawCircle(x.toFloat(), y.toFloat(), radius.toFloat(), paint)
        }
    }