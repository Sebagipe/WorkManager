package com.example.workmanager.viewModels

import androidx.lifecycle.ViewModel
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.ExistingWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.workmanager.workers.DataSyncWorker
import java.util.concurrent.TimeUnit

class DataViewModel : ViewModel(){
    fun initDataSync (workManager: WorkManager){
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresStorageNotLow(true)
            .build()

        val syncRequest = OneTimeWorkRequestBuilder<DataSyncWorker>()
            .setConstraints(constraints)
            .build()

        workManager.enqueueUniqueWork("Data_Sync", ExistingWorkPolicy.KEEP, syncRequest)
    }
    fun initPeriodicDataSync (workManager: WorkManager){
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresStorageNotLow(true)
            .build()

        val syncRequest = PeriodicWorkRequestBuilder<DataSyncWorker>(15,TimeUnit.SECONDS)
            .setConstraints(constraints)
            .build()

        workManager.enqueueUniquePeriodicWork("PERIODIC_DATA_SYNC", ExistingPeriodicWorkPolicy.KEEP, syncRequest)
    }
}