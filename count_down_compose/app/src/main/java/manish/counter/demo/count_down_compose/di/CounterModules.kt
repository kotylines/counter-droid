package manish.counter.demo.count_down_compose.di

import manish.counter.demo.count_down_compose.common.LocalNotificationHelper
import manish.counter.demo.count_down_compose.viewmodel.CounterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val counterModule = module {
    single { LocalNotificationHelper(get()) }
    viewModel { CounterViewModel(get()) }
}