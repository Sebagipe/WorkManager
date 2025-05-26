package com.example.workmanager.workers

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.workmanager.DataSyncRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

//TODO: Worker Klasse definieren (Siehe Vorlesungsfolien)
//      - In der Worker Klasse soll die DataSyncRepository instantiiert werden.
//        Die Repository repräsentiert eine Schnittstelle zu einen Dienst der die
//        Datensynchronisierung verwaltet (z.B mit Cloud Anbindung)
//      - Bei jeden Arbeitsauftrag soll überprüft werden ob die Daten aktuell sind.
//        Wenn nicht sollen die aktualisiert werden.
class DataSyncWorker{}