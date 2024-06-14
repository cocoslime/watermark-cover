package com.cocoslime.watermarkcover

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import androidx.annotation.Px
import androidx.appcompat.widget.AppCompatImageView
import com.cocoslime.watermarkcover.TextWatermarkDefault.DEFAULT_LETTER_SPACING_RATIO
import com.cocoslime.watermarkcover.TextWatermarkDefault.DEFAULT_LINE_SPACING_RATIO
import com.cocoslime.watermarkcover.TextWatermarkDefault.DEFAULT_ROTATION_DEGREE
import com.cocoslime.watermarkcover.TextWatermarkDefault.DEFAULT_TEXT_COLOR
import com.cocoslime.watermarkcover.TextWatermarkDefault.DEFAULT_TEXT_SIZE


open class TextWatermarkImageView @JvmOverloads constructor(
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
                attributeSet, R.styleable.TextWatermarkImageView, 0, 0
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

                invalidate()
                recycle()
            }
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawWatermark(
            watermarkText = watermarkText,
            paint = paint,
            width = width,
            height = height,
            rotationDegree = rotationDegree,
            letterSpacingRatio = letterSpacingRatio,
            lineSpacingRatio = lineSpacingRatio
        )
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
}
