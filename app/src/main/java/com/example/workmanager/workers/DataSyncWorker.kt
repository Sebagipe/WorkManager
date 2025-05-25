package com.example.workmanager.workers

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.workmanager.DataSyncRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class DataSyncWorker @AssistedInject constructor
    (@Assisted appContext : Context,
     @Assisted workerParams : WorkerParameters,
     private val dataSyncRepository : DataSyncRepository) :
    Worker(appContext, workerParams) {
    override fun doWork(): Result {
        try {
            dataSyncRepository.checkIfUpToDate()
            dataSyncRepository.syncData()
            return Result.success()
        } catch (e : Exception){
            return Result.failure()
        }
    }
}