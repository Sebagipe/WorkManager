package com.example.workmanager.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.work.WorkManager

class DataViewModel (app : Application) : AndroidViewModel(app) {

    private val workManager = WorkManager.getInstance(app.applicationContext)
    private val PERIODIC_DATA_SYNC_WORK_NAME = "PERIODIC_DATA_SYNC"

    fun initDataSync() {
        //TODO: Verwende den Workmanager um einen Arbeitsauftrag in die Warteschlange zu stellen
        // - Dabei sollen folgende Contraints eingehalten werden:
        //      - Internetverbindung muss vorhanden sein
        //      - Speicherplatz darf nicht niedrig sein
    }

    fun initPeriodicDataSync() {
        //TODO: Verwende den Workmanager um einen regelmäßigen Arbeitsauftrag in die Warteschlange
        // zu stellen
        // - Der Auftrag soll alle 15 Sekunden ausgeführt werden
        // - Dabei sollen folgende Contraints eingehalten werden:
        //      - Internetverbindung muss vorhanden sein
        //      - Speicherplatz darf nicht niedrig sein
        // - Der Aurbeitsauftrag soll als Unique in die Warteschlagen gestellt werden und soll
        //   den Namen "PERIODIC_DATA_SYNC" (Siehe variebel Oben)  bekommen.
        //   (Für die Workpolicy, einfach das nehmen: ExistingPeriodicWorkPolicy.KEEP)
    }

    fun cancelPeridocDataSync(){
        workManager.cancelUniqueWork(PERIODIC_DATA_SYNC_WORK_NAME)
    }
}