package com.example.taskroom.ui.Screens.HomeScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taskroom.data.remote.dto.TaskDto

@Composable
fun TaskCard(project:String, task: TaskDto, homeViewModel : HomeViewModel){
    Card(modifier = Modifier.padding(10.dp)) {
        Row(modifier = Modifier.padding(10.dp), verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = task.status == 2,
                onCheckedChange = { modalConfirmation = true },
                modifier = Modifier.weight(1f),
                colors = CheckboxDefaults.colors(
                    checkedColor = Color(255,180,1)
                )
            )
            Column(modifier = Modifier.weight(1.8f)) {
                Text(text = task.title, fontSize = 20.sp, overflow = TextOverflow.Ellipsis)
                Text(text = project, overflow = TextOverflow.Ellipsis)
            }
            var color = Color.LightGray
            var text = ""
            if (task.status == 0) {
                color = Color(158, 183, 229)
                text = "Started"
            } else if (task.status == 1) {
                color = Color(204, 41, 54)
                text = "Pending"
            } else if (task.status == 2) {
                color = Color(86, 227, 159)
                text = "Done"
            } else {
                color = Color.LightGray
                text = "none"
            }

            Card(modifier = Modifier.weight(1f), backgroundColor = color) {
                Text(
                    text = "State: $text",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(3.dp)
                )
            }
        }
    }
    if (modalConfirmation){
        if (task.status!=2){
            ConfirmationChangeStateTaskModal(homeViewModel = homeViewModel, task = task, state = 2)
        }
        else{
            ConfirmationChangeStateTaskModal(homeViewModel = homeViewModel, task = task, state = 1)
        }
    }

}