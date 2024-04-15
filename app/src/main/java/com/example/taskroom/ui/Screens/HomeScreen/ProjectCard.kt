package com.example.taskroom.ui.Screens.HomeScreen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Assignment
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.taskroom.data.remote.dto.ProjectDto
import com.example.taskroom.ui.util.AppScreens

@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
@Composable
fun ProjectCard(project: ProjectDto, percent:Int, homeViewModel: HomeViewModel,nav:NavController){

    Card(modifier = Modifier
        .padding(10.dp)
        .combinedClickable (onClick = {nav.navigate("${AppScreens.ProjectScreen.route}/${project.id}")}, onLongClick = { modalDeleteProjectOpen=true})
    ) {
        Row(modifier = Modifier.padding(10.dp)) {
            Icon(
                imageVector = Icons.Default.Assignment, contentDescription = "Project Icon",
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .size(100.dp),
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(2f)
            ) {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                    Text(text = project.endDate)
                }
                Text(
                    text = project.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    overflow = TextOverflow.Ellipsis
                )
                Text(text = project.description, overflow = TextOverflow.Ellipsis)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "${percent}%")
                    LinearProgressIndicator(
                        progress = percent.toFloat() / 100,
                        color = Color.Black,
                        backgroundColor = Color.Gray,
                        modifier = Modifier.padding(2.dp)
                    )
                }
            }
        }
    }
    if (modalDeleteProjectOpen){
        DeleteProjectModal(homeViewModel = homeViewModel, project = project)
    }

}