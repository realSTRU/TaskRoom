package com.example.taskroom.ui.Screens.HomeScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.taskroom.data.remote.dto.TaskDto

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConfirmationChangeStateTaskModal(homeViewModel: HomeViewModel,
                                     task: TaskDto,
                                     state:Int
) {
    androidx.compose.material3.AlertDialog(
        onDismissRequest = {
        }
    ) {
        Card {

            Column(modifier = Modifier.padding(20.dp)) {
                Text(text = "Do you want change task's status ?")
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp), horizontalArrangement = Arrangement.Center
                ) {

                    TextButton(onClick = { modalConfirmation = false })
                    {
                        Text(text = "NO", color = Color.Black)
                    }
                    Spacer(modifier = Modifier.padding(end = 10.dp))
                    Button(
                        onClick = {
                            homeViewModel.changeState(task = task, state = state)
                            modalConfirmation = false
                        },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color(255, 180, 1)
                        )
                    ) {
                        Text(text = "Yes")
                    }
                }
            }
        }
    }
}