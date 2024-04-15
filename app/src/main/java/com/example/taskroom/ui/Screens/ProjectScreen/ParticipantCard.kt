package com.example.taskroom.ui.Screens.ProjectScreen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taskroom.data.remote.dto.UserDto

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ParticipantCard(user: UserDto, role:Int, projectViewModel: ProjectViewModel){
    Card(
        modifier = Modifier
            .combinedClickable(onClick = {}, onLongClick = { projectViewModel.removeParticipant() })
            .padding(10.dp)
    ) {
        Row(
            modifier = Modifier.padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1.9f)) {
                Text(
                    text = "${user.name} ${user.surname}",
                    fontSize = 20.sp,
                    overflow = TextOverflow.Ellipsis
                )
                Text(text = "${user.username}#${user.id}", overflow = TextOverflow.Ellipsis)
            }
            if (role == 1) {
                Text(text = "Admin", overflow = TextOverflow.Ellipsis)
            } else {
                Text(text = "Collaborator", overflow = TextOverflow.Ellipsis)
            }
        }
    }
}