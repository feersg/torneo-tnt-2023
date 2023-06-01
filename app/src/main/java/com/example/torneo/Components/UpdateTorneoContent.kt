package com.example.torneo.Components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.torneo.Core.Data.Torneo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateTorneoContent(
    padding: PaddingValues,
    torneo: Torneo,
    updateNombre: (nombre:String) ->Unit,
    updateTipo: (tipo: String) -> Unit,
    updateTorneo: (torneo:Torneo) -> Unit,
    navigateBack: () -> Unit


){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = torneo.nombre,
            onValueChange = { nombre->
                updateNombre(nombre)
            },
            placeholder = {
                Text("Nombre del torneo")
            }
        )

        Spacer(modifier = Modifier.height(8.dp))
        torneo.tipo?.let {
            TextField(
                value = it,
                onValueChange = { tipo ->
                    updateTipo(tipo)
                },
                placeholder = {
                    Text("Tipo de torneo")
                }
            )
        }

        Button(
            onClick = {
                updateTorneo(torneo)
                navigateBack()
            }
        ) {
            Text(text = "Actualizar")
        }
    }
}