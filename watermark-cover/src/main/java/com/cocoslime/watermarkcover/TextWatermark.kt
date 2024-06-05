package com.cocoslime.watermarkcover

import android.graphics.Canvas
import android.graphics.Paint


fun Canvas.drawWatermark(
    watermarkText: String,
    paint: Paint,
    width: Int,
    height: Int,
    rotationDegree: Float,
    letterSpacingRatio: Float,
    lineSpacingRatio: Float,
) {
    val textWidth = paint.measureText(watermarkText)
    val textHeight = paint.fontMetrics.bottom - paint.fontMetrics.top

    if (watermarkText.isEmpty() || textWidth == 0f || textHeight == 0f) {
        return
    }

    val letterSpacing = textWidth * letterSpacingRatio
    val lineSpacing = textHeight * lineSpacingRatio

    this.save()
    this.rotate(rotationDegree, (width / 2).toFloat(), (height / 2).toFloat())

    var y = -height.toFloat()
    while (y < height * 2) {
        var x = -width.toFloat()
        while (x < width * 2) {
            this.drawText(watermarkText, x, y, paint)
            x += textWidth + letterSpacing
        }
        y += textHeight + lineSpacing
    }

    this.restore()
}