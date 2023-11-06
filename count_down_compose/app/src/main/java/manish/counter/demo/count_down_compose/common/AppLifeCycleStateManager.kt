package manish.counter.demo.count_down_compose.common

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ProcessLifecycleOwner

object AppLifeCycleStateManager : DefaultLifecycleObserver {

    var isInBackground = false

    fun start() {
        ProcessLifecycleOwner.get().lifecycle.addObserver(this)
    }

    fun stop() {
        ProcessLifecycleOwner.get().lifecycle.removeObserver(this)
    }

    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)
        isInBackground = false
    }

    override fun onPause(owner: LifecycleOwner) {
        super.onPause(owner)
        isInBackground = true
    }
}