package com.cocoslime.watermark_cover_view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import androidx.annotation.Px
import androidx.appcompat.widget.AppCompatImageView
import kotlin.random.Random

class TextWatermarkImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatImageView(context, attrs, defStyleAttr) {

    //region attributes

    private var watermarkText = "COCOSLIME"

    @Px private var spacedBy: Int = DEFAULT_LETTER_SPACING
    @Px private var lineSpacing: Int = DEFAULT_LINE_SPACING

    //endregion

    private val paint = Paint().apply {
        color = DEFAULT_TEXT_COLOR
        textSize = DEFAULT_TEXT_SIZE.toFloat()
        isAntiAlias = true
    }

    init {
        attrs?.let { attributeSet ->
            context.obtainStyledAttributes(
                attrs, R.styleable.TextWatermarkImageView, 0, 0
            ).run {
                getString(
                    R.styleable.TextWatermarkImageView_watermarkText
                )?.let {
                    watermarkText = it
                }

                getDimensionPixelSize(
                    R.styleable.TextWatermarkImageView_watermarkTextSize,
                    DEFAULT_TEXT_SIZE
                ).let {
                    paint.textSize = it.toFloat()
                }

                getColor(
                    R.styleable.TextWatermarkImageView_watermarkTextColor,
                    DEFAULT_TEXT_COLOR
                ).let {
                    paint.color = it
                }

                getDimensionPixelSize(
                    R.styleable.TextWatermarkImageView_watermarkTextLineSpacing,
                    DEFAULT_LINE_SPACING
                ).let {
                    lineSpacing = it
                }

                getDimensionPixelSize(
                    R.styleable.TextWatermarkImageView_watermarkTextLetterSpacing,
                    DEFAULT_LETTER_SPACING
                ).let {
                    spacedBy = it
                }

                recycle()
            }
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawWatermark(canvas)
    }

    private fun drawWatermark(canvas: Canvas) {
        val textWidth = paint.measureText(watermarkText)
        val textHeight = paint.fontMetrics.bottom - paint.fontMetrics.top

        var y = 0f
        while (y < height) {
            var x = Random.nextInt() % textWidth // random start
            while (x < width) {
                canvas.drawText(watermarkText, x, y, paint)
                x += textWidth + spacedBy.toFloat() // 텍스트 간 간격 조절
            }
            y += textHeight + lineSpacing.toFloat() // 텍스트 간 간격 조절
        }
    }

    //region setters

    fun setWatermarkText(text: String) {
        watermarkText = text
        invalidate()
    }

    fun setWatermarkTextColor(color: Int) {
        paint.color = color
        invalidate()
    }

    fun setWatermarkTextSize(@Px size: Float) {
        paint.textSize = size
        invalidate()
    }

    fun setWatermarkTextSpacing(@Px spacing: Int) {
        spacedBy = spacing
        invalidate()
    }

    fun setWatermarkTextLineSpacing(@Px spacing: Int) {
        lineSpacing = spacing
        invalidate()
    }

    //endregion

    companion object {
        private const val DEFAULT_TEXT_SIZE = 48
        private const val DEFAULT_TEXT_COLOR = Color.WHITE
        private const val DEFAULT_LINE_SPACING = 100
        private const val DEFAULT_LETTER_SPACING = 100
    }
}
