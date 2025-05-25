package com.example.workmanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.ExistingWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.workmanager.ui.theme.WorkManagerTheme
import com.example.workmanager.workers.DataSyncWorker
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit
import javax.inject.Inject

lateinit var workManager: WorkManager



@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var repository: DataSyncRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        workManager = WorkManager.getInstance(applicationContext)
        enableEdgeToEdge()
        setContent {
            WorkManagerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(Modifier.padding(innerPadding)) {
                        val isUpToDate by remember { repository.isUpToDate }
                        Text(isUpToDate.toString())
                        Button(onClick = { initDataSync() }) {
                            Text("Sync Data")
                        }
                        Row {
                            var checked by remember { mutableStateOf(false) }
                            Switch(
                                checked = checked,
                                onCheckedChange = {
                                    if (checked == false) {
                                        checked = true
                                        initPeriodicDataSync()
                                    } else {
                                        checked = false
                                        workManager.cancelUniqueWork("PERIODIC_DATA_SYNC")
                                    }
                                }
                            )
                            Spacer(Modifier.padding(5.dp))
                            Text(
                                "Sync data every minute",
                                Modifier.align(Alignment.CenterVertically)
                            )
                        }
                    }
                }
            }
        }
    }
}


fun initDataSync (){
    val constraints = Constraints.Builder()
        .setRequiredNetworkType(NetworkType.CONNECTED)
        .setRequiresStorageNotLow(true)
        .build()

    val syncRequest = OneTimeWorkRequestBuilder<DataSyncWorker>()
        .setConstraints(constraints)
        .build()

    workManager.enqueueUniqueWork("Data_Sync", ExistingWorkPolicy.KEEP, syncRequest)
}
fun initPeriodicDataSync (){
    val constraints = Constraints.Builder()
        .setRequiredNetworkType(NetworkType.CONNECTED)
        .setRequiresStorageNotLow(true)
        .build()

    val syncRequest = PeriodicWorkRequestBuilder<DataSyncWorker>(1,TimeUnit.MINUTES)
        .setConstraints(constraints)
        .build()

    workManager.enqueueUniquePeriodicWork("PERIODIC_DATA_SYNC", ExistingPeriodicWorkPolicy.KEEP, syncRequest)
}
