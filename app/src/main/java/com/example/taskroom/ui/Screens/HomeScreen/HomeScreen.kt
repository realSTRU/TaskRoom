package com.example.taskroom.ui.Screens.HomeScreen

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.taskroom.R
import com.example.taskroom.currentUser
import com.example.taskroom.data.localStorage.SessionStorage
import com.example.taskroom.data.remote.dto.ProjectDto
import com.example.taskroom.ui.util.AppScreens


//Modal open variable
var modalNewProjectOpen by mutableStateOf(false)
var modalNewProfileOpen by mutableStateOf(false)
var modalConfirmation by mutableStateOf(false)
var modalDeleteProjectOpen by mutableStateOf(false)
var modalAddParticipantOpen by mutableStateOf(false)
var modalAddTaskOpen by mutableStateOf(false)

@Composable
fun Homescreen(context:Context,nav: NavController,storage:SessionStorage,homeViewModel: HomeViewModel){
    LaunchedEffect (currentUser){
        homeViewModel.load()
    }

    var projectSlected by remember { mutableStateOf(true) }
    var colorProjectText by remember { mutableStateOf(Color(255,180,1)) }
    var colorTaskText by remember { mutableStateOf(Color.Gray) }

    val background = painterResource(id = R.drawable.backgroundhome)
    Image(painter = background, contentDescription ="Background",
        contentScale = ContentScale.FillBounds,
        modifier = Modifier
            .fillMaxSize() )

    
    Surface(
       modifier = Modifier.padding(20.dp),
        color = Color.Transparent
    ){
        Column(modifier= Modifier
            .padding(10.dp)
            .fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "HI ${currentUser.name} ${currentUser.surname}!", color = Color(238,240,242), fontWeight = FontWeight.Light, fontSize = 20.sp)
        }
        Column(horizontalAlignment = Alignment.End) {
            IconButton(onClick = { modalNewProfileOpen = !modalNewProfileOpen }) {
                Icon(imageVector = Icons.Default.Settings, contentDescription = "Settings",tint=Color.White)
            }
        }
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(top = 150.dp)) {
       Row(modifier = Modifier
           .fillMaxWidth()
           .padding(start = 20.dp, end = 20.dp), horizontalArrangement = Arrangement.Center) {
           TextButton(onClick = {
               projectSlected=!projectSlected
               colorProjectText = Color(255,180,1)
               colorTaskText =  Color.Gray
           }
               , enabled = !projectSlected
               , modifier = Modifier.weight(1f)
           ) {
               Column (horizontalAlignment = Alignment.End){
                   Text(text = "PROJECTS", color = colorProjectText ,fontSize = 20.sp)
                   Divider(thickness = 3.dp,color = colorProjectText)
               }
           }
           TextButton(onClick = {
               projectSlected=!projectSlected
               colorProjectText = Color.Gray
               colorTaskText =  Color(255,180,1)
           }
               , enabled = projectSlected
               , modifier = Modifier.weight(1f)
           ) {
               Column {
                   Text(text = "TASKS", color = colorTaskText , fontSize = 20.sp)
                   Divider(thickness = 3.dp,color = colorTaskText)
               }

           }
       }
        
        if (projectSlected){
            LazyColumn {
                items(homeViewModel.projectList){ project ->
                    ProjectCard(project=project, percent = calcularPorciento(project) , homeViewModel=homeViewModel, nav=nav )
                }
            }
        }
        else{
            LazyColumn {
                items(homeViewModel.taskList) { task ->
                    TaskCard(project = task.title, task = task, homeViewModel=homeViewModel )
                }
            }
        }
       
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(20.dp), horizontalAlignment = Alignment.End, verticalArrangement = Arrangement.Bottom) {
        if (projectSlected){
            FloatingActionButton(
                onClick = {  modalNewProjectOpen = !modalNewProjectOpen },
                backgroundColor = Color(255,180,1)
            ) {
                Text("+", fontSize = 30.sp)
            }
        }
    }

    if(modalNewProjectOpen){
        NewProjectModal(context,homeViewModel)
    }
    if(modalNewProfileOpen){
        ProfileViewModal(storage,nav)
    }
}
fun calcularPorciento(project: ProjectDto): Int {
    try {
        var percent = ((project.tasks.filter { o-> o.status ==2 }.count() * 100) / project.tasks.count()).toInt()
        return percent
    }
    catch (e: Exception){
        return 0
    }
}

