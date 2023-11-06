package manish.counter.demo.count_down_compose.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import manish.counter.demo.count_down_compose.R
import manish.counter.demo.count_down_compose.common.format
import manish.counter.demo.count_down_compose.common.rangedSecondFromMilliSecond
import manish.counter.demo.count_down_compose.model.CounterState

@Composable
fun CounterView(
    modifier: Modifier,
    counterValue: Long,
    counterState: CounterState,
    onRunClick: () -> Unit,
    onStopClick: () -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            CircularProgressBar(
                progress = rangedSecondFromMilliSecond(counterValue),
                modifier = Modifier
                    .size(200.dp)
            )

            Text(
                text = format(counterValue),
                style = TextStyle(fontSize = 32.sp),
                modifier = Modifier.padding(16.dp)
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Bottom,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Button(
                onClick = onRunClick,
                modifier = Modifier
                    .padding(end = 8.dp)
                    .height(50.dp)
            ) {
                Text(text = counterState.actionTitle())
            }

            Button(
                onClick = onStopClick,
                enabled = !counterState.isInit(),
                modifier = Modifier
                    .height(50.dp)
            ) {
                Text(text = stringResource(id = R.string.stop))
            }
        }
    }
}


@Preview
@Composable
fun CounterViewPreview() {
    CounterView(
        Modifier
            .fillMaxSize()
            .background(Color.White),
        30*1000,
        CounterState.PAUSED,
        onRunClick = {},
        onStopClick = {}
    )
}