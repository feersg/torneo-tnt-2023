package com.example.torneo.Components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import com.example.torneo.Core.Constantes.Companion.DISMISS
import com.example.torneo.Core.Constantes.Companion.NO_VALUE
import com.example.torneo.Core.Data.Entity.Fecha

import kotlinx.coroutines.job

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddFechasAlertDialog(
    torneoId : Int,
    openDialog: Boolean,
    closeDialog: ()->Unit,
    addFecha: (fecha: Fecha) -> Unit
){
    if (openDialog){
        var numero by remember { mutableStateOf(NO_VALUE) }
        val focusRequester = FocusRequester()

        AlertDialog(
            onDismissRequest = { closeDialog },
            title = {
                Text("Agregar Fecha")
            },
            text = {
                Column{
                    TextField(
                        label = { Text(text = "Numero de fecha") },
                        singleLine = true,
                        value = numero,
                        onValueChange = {numero = it},
                        modifier = Modifier.focusRequester(focusRequester)
                    )
                    LaunchedEffect(Unit){
                        coroutineContext.job.invokeOnCompletion {
                            focusRequester .requestFocus()
                        }
                    }
                }
            },
            confirmButton = {
                TextButton(
                    onClick = { closeDialog()
                        val fecha = Fecha(0, idTorneo = torneoId.toString(), numero = numero, estado=" Empezado")
                        addFecha(fecha)
                    },
                    enabled = !(numero.isBlank())
                ){
                    Text(text = ("Agregar Fecha"))
                }
            },
            dismissButton = {
                TextButton(onClick = closeDialog) {
                    Text(text = DISMISS)
                }
            }
        )
    }
}