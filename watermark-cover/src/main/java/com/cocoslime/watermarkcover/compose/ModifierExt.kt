package com.cocoslime.watermarkcover.compose

import android.graphics.Paint
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import com.cocoslime.watermarkcover.TextWatermarkDefault.DEFAULT_LETTER_SPACING_RATIO
import com.cocoslime.watermarkcover.TextWatermarkDefault.DEFAULT_LINE_SPACING_RATIO
import com.cocoslime.watermarkcover.TextWatermarkDefault.DEFAULT_ROTATION_DEGREE
import com.cocoslime.watermarkcover.TextWatermarkDefault.DEFAULT_TEXT_COLOR
import com.cocoslime.watermarkcover.TextWatermarkDefault.DEFAULT_TEXT_SIZE
import com.cocoslime.watermarkcover.drawWatermark


fun Modifier.drawWatermarkText(
    watermarkText: String,
    textColor: Color = Color(DEFAULT_TEXT_COLOR),
    watermarkTextSize: Int = DEFAULT_TEXT_SIZE,
    rotationDegree: Float = DEFAULT_ROTATION_DEGREE,
    lineSpacingRatio: Float = DEFAULT_LINE_SPACING_RATIO,
    letterSpacingRatio: Float = DEFAULT_LETTER_SPACING_RATIO,
): Modifier = this.drawWithContent {
    drawContent()
    drawIntoCanvas { canvas ->
        val paint = Paint().apply {
            color = textColor.toArgb()
            textSize = watermarkTextSize.toFloat()
            isAntiAlias = true
        }
        val width = this.size.width
        val height = this.size.height
        canvas.nativeCanvas.clipRect(0f, 0f, width, height)
        canvas.nativeCanvas.drawWatermark(
            watermarkText = watermarkText,
            paint = paint,
            width = width.toInt(),
            height = height.toInt(),
            rotationDegree = rotationDegree,
            letterSpacingRatio = letterSpacingRatio,
            lineSpacingRatio = lineSpacingRatio
        )
    }
}