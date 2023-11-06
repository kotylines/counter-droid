package manish.counter.demo.count_down_compose.model

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import manish.counter.demo.count_down_compose.R

enum class CounterState {
    INIT,
    RUNNING,
    PAUSED;

    fun isInit(): Boolean { return this == INIT }

    fun isRunning(): Boolean { return this == RUNNING }

    fun isPaused(): Boolean { return this == PAUSED }

    @Composable
    fun actionTitle(): String {
        return if (isRunning()) {
            stringResource(id = R.string.pause)
        } else if (isPaused()) {
            stringResource(id = R.string.resume)
        } else {
            stringResource(id = R.string.start)
        }
    }
}