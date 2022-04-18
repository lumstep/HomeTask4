package com.lumstep.clock

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import java.util.*
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin
import kotlin.properties.Delegates

class HoursArrow @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private var paintLine = Paint(Paint.ANTI_ALIAS_FLAG)
    private var hours = Calendar.getInstance().get(Calendar.HOUR)
    private val attributes = context.obtainStyledAttributes(attrs, R.styleable.HoursArrow)

    private var length by Delegates.notNull<Float>()
    private lateinit var timer: Timer
    private lateinit var timerTask: TimerTask

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        paintLine.apply {
            style = Paint.Style.FILL
            color = attributes.getColor(R.styleable.HoursArrow_color, Color.BLACK)
            strokeWidth = attributes.getDimension(R.styleable.HoursArrow_width, 20f)
        }

        length = attributes.getDimension(R.styleable.HoursArrow_length, 260f)

        attributes.recycle()

        timer = Timer()

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        timerTask = object : TimerTask() {
            override fun run() {
                invalidate()
            }
        }

        timer.schedule(timerTask, 3600000L)

        val centerX = (width / 2).toFloat()
        val centerY = (height / 2).toFloat()

        val stopX = centerX + (length * RATIO_OF_ARROWS * sin(((PI / 30) * hours).toFloat()))
        val startX =
            centerX - (length * RATIO_OF_ARROWS * sin(((PI / 30) * hours).toFloat())) * (1 - RATIO_OF_ARROWS) / RATIO_OF_ARROWS
        val stopY = centerY - (length * RATIO_OF_ARROWS * cos(((PI / 30) * hours).toFloat()))
        val startY =
            centerY + (length * RATIO_OF_ARROWS * cos(((PI / 30) * hours).toFloat())) * (1 - RATIO_OF_ARROWS) / RATIO_OF_ARROWS

        canvas?.drawLine(
            startX,
            startY,
            stopX,
            stopY,
            paintLine
        )

        if (hours == 60) hours = 0
        else hours++
    }
}