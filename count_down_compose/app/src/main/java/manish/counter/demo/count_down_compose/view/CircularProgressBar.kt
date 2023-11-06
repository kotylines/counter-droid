package manish.counter.demo.count_down_compose.view

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CircularProgressBar(
    progress: Float,
    modifier: Modifier = Modifier,
    color: Color = Color.Green
) {
    Canvas(
        modifier = modifier
    ) {
        val center = Offset(size.width / 2f, size.height / 2f)
        val radius = size.width / 2f
        val strokeWidth = 10f

        drawCircle(
            color = Color.LightGray,
            center = center,
            radius = radius,
            style = Stroke(width = strokeWidth)
        )

        drawArc(
            color = color,
            startAngle = 270f,
            sweepAngle = 360f * progress,
            useCenter = false,
            style = Stroke(width = strokeWidth + 15)
        )
    }
}

@Preview
@Composable
fun CircularProgressBarPreview() {
    CircularProgressBar(
        0.75f,
        Modifier
            .size(200.dp)
            .background(Color.White)
    )
}