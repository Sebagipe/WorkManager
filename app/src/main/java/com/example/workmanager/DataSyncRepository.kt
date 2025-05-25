package com.example.workmanager

import android.content.Context
import androidx.compose.runtime.mutableStateOf

class DataSyncRepository (applicationContext : Context) {
    val isUpToDate = mutableStateOf(false)
    fun syncData(){
        Thread.sleep(3000)
        println("Data synced")
        isUpToDate.value = true
    }
    fun checkIfUpToDate(){
        // Nonsense Code for Testing periodic work
        if (isUpToDate.value == true)
            isUpToDate.value = false
        else
            isUpToDate.value = true
    }
}