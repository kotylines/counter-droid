package manish.counter.demo.count_down_compose.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import manish.counter.demo.count_down_compose.common.AppLifeCycleStateManager
import manish.counter.demo.count_down_compose.common.LocalNotificationHelper
import manish.counter.demo.count_down_compose.model.CounterState

class CounterViewModel(
    private val notificationHelper: LocalNotificationHelper
): ViewModel() {

    private val oneMin = 60 * 1000L

    private val _currentCounterValue = MutableStateFlow(oneMin)
    val currentCounterValue: StateFlow<Long> get() = _currentCounterValue

    private val _currentCounterState = MutableStateFlow(CounterState.INIT)
    val currentCounterState: StateFlow<CounterState> get() = _currentCounterState

    private val _notificationPermissionState = MutableStateFlow(notificationHelper.checkIfHavePermission())
    val notificationPermissionState: StateFlow<Boolean> get() = _notificationPermissionState

    private var counterJob: Job? = null

    private fun hasElapsed(): Boolean {
        return _currentCounterValue.value <= 0L
    }

    private fun startCounter() {
        _currentCounterState.value = CounterState.RUNNING

        counterJob = viewModelScope.launch {
            while (currentCounterState.value.isRunning()) {
                if (hasElapsed()) {
                    if(AppLifeCycleStateManager.isInBackground) {
                        notificationHelper.showTimerNotification()
                    }
                    stop()
                    break
                }

                _currentCounterValue.value -= 10
                delay(10) // trigger every 10ms
            }
        }
    }

    private fun start() {
        startCounter()
    }

    private fun pause() {
        _currentCounterState.value = CounterState.PAUSED
        counterJob?.cancel()
    }

    private fun resume() {
        startCounter()
    }

    fun runCounter() {
        when(currentCounterState.value) {
            CounterState.INIT -> start()
            CounterState.RUNNING -> pause()
            CounterState.PAUSED -> resume()
        }
    }

    fun stop() {
        _currentCounterState.value = CounterState.INIT
        counterJob?.cancel()
        _currentCounterValue.value = oneMin
    }

    fun updateUserResponseToNotificationPermissionRequest(status: Boolean) {
        _notificationPermissionState.value = status
    }

}