package manish.counter.demo.count_down_compose

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import manish.counter.demo.count_down_compose.theme.Count_down_composeTheme
import manish.counter.demo.count_down_compose.view.CounterView
import manish.counter.demo.count_down_compose.viewmodel.CounterViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    private val counterViewModel: CounterViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Count_down_composeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val counterValue by counterViewModel.currentCounterValue.collectAsState()
                    val counterState by counterViewModel.currentCounterState.collectAsState()
                    val notificationPermissionState by counterViewModel.notificationPermissionState.collectAsState()

                    val launcher = rememberLauncherForActivityResult(
                        ActivityResultContracts.RequestPermission()
                    ) { isGranted: Boolean ->
                        counterViewModel.updateUserResponseToNotificationPermissionRequest(isGranted)
                    }

                    Column(
                        Modifier
                            .fillMaxSize()
                    ) {
                        if (!notificationPermissionState) {
                            SideEffect {
                                launcher.launch(Manifest.permission.POST_NOTIFICATIONS)
                            }

                            Text(
                                text = "Notification Permission is not granted",
                                color = Color.Red
                            )
                        }

                        CounterView(
                            Modifier
                                .fillMaxSize(),
                            counterValue,
                            counterState,
                            onRunClick = {
                                counterViewModel.runCounter()
                            },
                            onStopClick = {
                                counterViewModel.stop()
                            }
                        )
                    }

                }
            }
        }
    }
}