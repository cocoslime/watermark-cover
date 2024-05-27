package com.cocoslime.watermark_cover_view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import androidx.annotation.Px
import androidx.appcompat.widget.AppCompatImageView

class TextWatermarkImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatImageView(context, attrs, defStyleAttr) {

    //region attributes

    private var watermarkText = "COCOSLIME"

    private var rotationDegree: Float = DEFAULT_ROTATION_DEGREE
    /**
     * 텍스트 간 간격 / 텍스트 길이의 비율
     */
    private var letterSpacingRatio: Float = DEFAULT_LETTER_SPACING_RATIO

    /**
     * 줄 간 간격 / 텍스트 높이의 비율
     */
    private var lineSpacingRatio: Float = DEFAULT_LINE_SPACING_RATIO

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

                getFloat(
                    R.styleable.TextWatermarkImageView_watermarkTextRotationDegree,
                    DEFAULT_ROTATION_DEGREE
                ).let {
                    rotationDegree = it
                }

                getFloat(
                    R.styleable.TextWatermarkImageView_watermarkTextLineSpacingRatio,
                    DEFAULT_LINE_SPACING_RATIO
                ).let {
                    lineSpacingRatio = it
                }

                getFloat(
                    R.styleable.TextWatermarkImageView_watermarkTextLetterSpacingRatio,
                    DEFAULT_LETTER_SPACING_RATIO
                ).let {
                    letterSpacingRatio = it
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
        val letterSpacing = textWidth * letterSpacingRatio
        val lineSpacing = textHeight * lineSpacingRatio

        canvas.save()
        canvas.rotate(rotationDegree, (width / 2).toFloat(), (height / 2).toFloat())

        var y = -height.toFloat()
        while (y < height * 2) {
            var x = -width.toFloat()
            while (x < width * 2) {
                canvas.drawText(watermarkText, x, y, paint)
                x += textWidth + letterSpacing
            }
            y += textHeight + lineSpacing
        }

        canvas.restore()
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

    //endregion

    companion object {
        private const val DEFAULT_TEXT_SIZE = 48
        private const val DEFAULT_TEXT_COLOR = Color.WHITE
        private const val DEFAULT_ROTATION_DEGREE = -45f
        private const val DEFAULT_LINE_SPACING_RATIO: Float = 2f
        private const val DEFAULT_LETTER_SPACING_RATIO: Float = 1f
    }
}
