package com.example.workmanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
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
import com.example.workmanager.ui.theme.WorkManagerTheme
import com.example.workmanager.viewModels.DataViewModel

class MainActivity : ComponentActivity() {
    lateinit var repository: DataSyncRepository
    private val viewModel : DataViewModel  by viewModels<DataViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WorkManagerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(Modifier.padding(innerPadding)) {
                    //TODO:
                    // Verwende den viewModel, um eine Datensynchronisierung in der Warteschlange zu setzen
                        Button(onClick = { }) {
                            Text("Sync Data")
                        }
                        Row {
                            //TODO:
                            // Verwende den viewModel, um eine periodische Datensynchronisierung mit den folgenden Switch an- und aus-schalten zu können.
                            var checked by remember { mutableStateOf(false) }
                            Switch(
                                checked = checked,
                                onCheckedChange = {
                                    if (checked == false) {
                                        checked = true
                                    } else {
                                        checked = false
                                    }
                                }
                            )
                            Spacer(Modifier.padding(5.dp))
                            Text(
                                "Sync data every 15 seconds",
                                Modifier.align(Alignment.CenterVertically)
                            )
                        }
                    }
                }
            }
        }
    }
}

