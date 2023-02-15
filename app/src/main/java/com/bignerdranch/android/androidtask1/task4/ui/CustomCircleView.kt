package com.bignerdranch.android.androidtask1.task4.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.bignerdranch.android.androidtask1.R

class CustomCircleView(context: Context,
                       attrs: AttributeSet? = null
) : View(context, attrs) {


    val paint = Paint().apply {
        style = Paint.Style.FILL_AND_STROKE
    }
    private var circleRadius = 0f
    private var circleColor = 0

    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomCircleView)
        circleRadius = typedArray.getDimension(R.styleable.CustomCircleView_circleRadius, 0f)
        circleColor = typedArray.getColor(R.styleable.CustomCircleView_circleColor, 0)
        typedArray.recycle()
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        paint.color = circleColor
        val x = width / 2
        val y = height / 2
        canvas.drawCircle(x.toFloat(), y.toFloat(), circleRadius, paint)
        }
    }