package com.example.torneo.Components.Usuario

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.torneo.Core.Data.Entity.Partido

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PartidoUsuarioCard(
    partido: Partido,
    deletePartido: ()-> Unit,
    navigateToUpdatePartidoScreen: (partidoId: Int)-> Unit
){
    Card(
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier
            .padding(
                start = 8.dp,
                end = 8.dp,
                top = 4.dp,
                bottom = 4.dp
            )
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation() ,
        onClick = {
            navigateToUpdatePartidoScreen(partido.id)
        }

    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Column() {
                Text(
                    text = "${partido.idLocal} - ${partido.idVisitante}",
                    fontWeight = FontWeight.Bold
                )
                Text(text = "Horario : ${partido.hora} - ${partido.dia}")
                Text(text = "Juez : ${partido.idPersona}")
                Text(text = "Cancha : ${partido.numCancha}")
                Text(text = "Resultado : ${partido.resultado}")
            }
            Spacer(
                modifier = Modifier.weight(1f)
            )
            IconButton(onClick = {navigateToUpdatePartidoScreen(partido.id)}) {
                Icon(imageVector = Icons.Default.Edit, contentDescription = "Editar Partido" )
            }
            IconButton(onClick = deletePartido ) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "Borrar Partido" )
            }

        }
    }
}
