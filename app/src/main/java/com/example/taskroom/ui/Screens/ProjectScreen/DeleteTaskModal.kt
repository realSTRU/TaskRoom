package com.example.taskroom.ui.Screens.ProjectScreen

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
import com.example.taskroom.data.remote.api.projectApi
import com.example.taskroom.data.remote.dto.ProjectDto
import com.example.taskroom.data.remote.dto.TaskDto
import com.example.taskroom.ui.Screens.HomeScreen.HomeViewModel
import com.example.taskroom.ui.Screens.HomeScreen.modalDeleteProjectOpen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeleteTaskModal(projectViewModel: ProjectViewModel,
                       task: TaskDto
) {
    androidx.compose.material3.AlertDialog(
        onDismissRequest = {
        }
    ) {
        Card {
            Column(modifier = Modifier.padding(20.dp)) {
                Text(text = "Do you want delete this task?")
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp), horizontalArrangement = Arrangement.Center
                ) {

                    TextButton(onClick = { modalDeleteProjectOpen = false })
                    {
                        Text(text = "NO", color = Color.Black)
                    }

                    Spacer(modifier = Modifier.padding(end = 10.dp))
                    Button(
                        onClick = {
                            projectViewModel.deleteTask(task = task)

                            modalDeleteProjectOpen = false
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
