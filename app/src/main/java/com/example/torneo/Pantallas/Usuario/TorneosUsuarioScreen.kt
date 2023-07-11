package com.example.torneo.Pantallas.Usuario

import Component.CustomTopAppBar
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.torneo.Components.AddTorneoFlotingActionButton
import com.example.torneo.Components.AddTorneosAlertDialog
import com.example.torneo.Components.TorneosContent
import com.example.torneo.Components.Usuario.TorneosUsuarioContent
import com.example.torneo.TorneoViewModel.TorneosViewModel

@Composable
fun TorneosUsuarioScreen(
    viewModel: TorneosViewModel = hiltViewModel(),
    navController: (torneoId: Int) -> Unit,
    navigateToFechaScreen: (torneoId: Int) -> Unit,
    navControllerBack: NavHostController
){
    Box(modifier = Modifier.fillMaxSize()) {
        ScaffoldWithTopBarTorneosUsuarioScreen(viewModel, navController, navigateToFechaScreen, navControllerBack)
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldWithTopBarTorneosUsuarioScreen(
    viewModel: TorneosViewModel = hiltViewModel(),
    navController: (torneoId: Int) -> Unit,
    navigateToFechaScreen: (torneoId: Int) -> Unit,
    navControllerBack: NavHostController,
){

    val torneos by viewModel.torneos.collectAsState(initial = emptyList() )

    var mostrarFinalizados = remember { mutableStateOf(false) }
    var mostrarEnCurso = remember { mutableStateOf(false) }
    var mostrarTodos = remember { mutableStateOf(false) }

    Scaffold (
        topBar = {
            CustomTopAppBar(navControllerBack, "Torneos", true)
            /*TopAppBar(
                title = {Text(TORNEOS_SCREEN + "     ", modifier = Modifier.fillMaxWidth())})*/
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.height(10.dp))
                Button(
                    onClick = {
                        mostrarFinalizados.value = false
                        mostrarEnCurso.value = false
                        mostrarTodos.value = true
                    }
                ) {
                    androidx.compose.material.Text(text = "Todos")
                }
                Button(
                    onClick = {
                        mostrarFinalizados.value = false
                        mostrarEnCurso.value = true
                        mostrarTodos.value = false
                    }
                ) {
                    androidx.compose.material.Text(text = "en curso")
                }
                Button(
                    onClick = {
                        mostrarFinalizados.value = true
                        mostrarEnCurso.value = false
                        mostrarTodos.value = false
                    }
                ) {
                    androidx.compose.material.Text(text = "Finalizados")
                }

            }
        },
        content = { padding ->
            TorneosUsuarioContent(
                padding = padding,
                torneos = torneos,
                deleteTorneo = { torneo ->
                    viewModel.deleteTorneo(torneo)
                },
                navigateToUpdateTorneoScreen = navController,
                navigateToFechaScreen, mostrarTodos.value, mostrarFinalizados.value,
                mostrarEnCurso.value
        )})
}


