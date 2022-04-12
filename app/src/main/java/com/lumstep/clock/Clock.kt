package com.lumstep.clock

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

const val RATIO_OF_ARROWS = 0.7f

class Clock @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {



    private val paintCircle = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        color = Color.BLACK
        strokeWidth = 15f
    }
    private val paintLine = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        color = Color.BLACK
        strokeWidth = 15f
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        val radius = (width / 4).toFloat()
        val centerX = (width / 2).toFloat()
        val centerY = (height / 2).toFloat()

        canvas?.drawCircle((width / 2).toFloat(), (height / 2).toFloat(), radius, paintCircle)

        for (i in 1..12) {
            val stopX = centerX - (radius * sin(((PI / 6) * i).toFloat()))
            val startX = centerX - (radius * 0.85f * sin(((PI / 6) * i).toFloat()))
            val stopY = centerY - (radius * cos(((PI / 6) * i).toFloat()))
            val startY = centerY - (radius * 0.85f * cos(((PI / 6) * i).toFloat()))

            canvas?.drawLine(
                startX,
                startY,
                stopX,
                stopY,
                paintLine
            )
        }
    }
}