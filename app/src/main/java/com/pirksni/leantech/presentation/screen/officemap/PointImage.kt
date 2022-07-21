package com.pirksni.leantech.presentation.screen.officemap

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.widget.LinearLayout
import com.pirksni.leantech.R


class PointImage constructor(
    context: Context,
    attr: AttributeSet? = null,
    defStyleAttr: Int
) : LinearLayout(context, attr, defStyleAttr) {


    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {

        val marker = BitmapFactory.decodeResource(
            context.resources,
            R.drawable.ic_location
        )
        canvas?.drawBitmap(marker, 40f, 40f, null)
        val mPaint = Paint()
        mPaint.color = Color.RED
        canvas!!.drawCircle(60f, 60f, 5f, mPaint)
        super.onDraw(canvas)
    }
}
