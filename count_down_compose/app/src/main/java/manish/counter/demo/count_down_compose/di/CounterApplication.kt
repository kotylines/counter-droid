package manish.counter.demo.count_down_compose.di

import android.app.Application
import manish.counter.demo.count_down_compose.common.AppLifeCycleStateManager
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class CounterApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        AppLifeCycleStateManager.start()

        startKoin {
            androidContext(this@CounterApplication)
            modules(counterModule)
        }
    }

    override fun onTerminate() {
        AppLifeCycleStateManager.stop()
        super.onTerminate()
    }

}