package com.example.taskroom.ui.Screens.ProjectScreen

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.taskroom.R
import com.example.taskroom.currentUser
import com.example.taskroom.data.localStorage.SessionStorage
import com.example.taskroom.ui.Screens.HomeScreen.HomeViewModel
import com.example.taskroom.ui.Screens.HomeScreen.ProfileViewModal
import com.example.taskroom.ui.Screens.HomeScreen.modalAddParticipantOpen
import com.example.taskroom.ui.Screens.HomeScreen.modalNewProfileOpen
import com.example.taskroom.ui.Screens.HomeScreen.modalNewProjectOpen
import com.example.taskroom.ui.util.AppScreens

@Composable
fun ProjectScreen(context: Context, nav: NavController, storage: SessionStorage, id: Int, projectViewModel: ProjectViewModel,homeViewModel:HomeViewModel ){
    LaunchedEffect(key1 =true) {
        projectViewModel.loadProject(id)
    }
    if (id == projectViewModel.currentProject.id){

        var tasksSelected by remember { mutableStateOf(true) }
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
                    Icon(imageVector = Icons.Default.Settings, contentDescription = "Settings",tint= Color.White)
                }
            }
        }

        Column(modifier = Modifier
            .fillMaxSize()
            .padding(top = 150.dp)) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { nav.navigate(AppScreens.HomeScreen.route) },modifier = Modifier.weight(1f)) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                }
                Row (horizontalArrangement = Arrangement.Center,modifier = Modifier.weight(1.5f)){
                    Text(text = projectViewModel.currentProject.title , fontSize = 30.sp, overflow = TextOverflow.Ellipsis)
                }
                Spacer(modifier = Modifier.weight(1f))
            }
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)) {
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp), horizontalArrangement = Arrangement.Center) {
                    TextButton(onClick = {
                        tasksSelected=!tasksSelected
                        colorProjectText = Color(255,180,1)
                        colorTaskText =  Color.Gray
                    }
                        , enabled = !tasksSelected
                        , modifier = Modifier.weight(1f)
                    ) {
                        Column (horizontalAlignment = Alignment.CenterHorizontally){
                            Text(text = "TASKS", color = colorProjectText ,fontSize = 20.sp)
                            Divider(thickness = 3.dp,color = colorProjectText)
                        }
                    }
                    TextButton(onClick = {
                        tasksSelected=!tasksSelected
                        colorProjectText = Color.Gray
                        colorTaskText =  Color(255,180,1)
                    }
                        , enabled = tasksSelected
                        , modifier = Modifier.weight(1f)
                    ) {
                        Column (horizontalAlignment = Alignment.CenterHorizontally){
                            Text(text = "PARTICIPANTS", color = colorTaskText , fontSize = 17.sp, overflow = TextOverflow.Ellipsis)
                            Divider(thickness = 3.dp,color = colorTaskText)
                        }

                    }
                }

                if (!tasksSelected){
                    LazyColumn {
                        items(projectViewModel.roleUserList){ roleUser ->
                            ParticipantCard(user = roleUser.user,projectViewModel=projectViewModel,role = roleUser.role)
                        }
                    }
                }
                else{
                    LazyColumn {
                        items(projectViewModel.taskUserList) { taskUser ->
                            TaskProjectCard(task = taskUser.task ,username = taskUser.user.username, homeViewModel = homeViewModel, projectViewModel = projectViewModel)
                        }
                    }
                }

            }

        }
        if(modalNewProfileOpen){
            ProfileViewModal(storage,nav)
        }

        //Botones de accion
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(20.dp), horizontalAlignment = Alignment.End, verticalArrangement = Arrangement.Bottom) {
            if (tasksSelected){
                FloatingActionButton(
                    onClick = {  modalNewProjectOpen = !modalNewProjectOpen },
                    backgroundColor = Color(255,180,1)
                ) {
                    Text("+", fontSize = 30.sp)
                }
            }
            else{
                FloatingActionButton(
                    onClick = {  modalAddParticipantOpen = !modalAddParticipantOpen },
                    backgroundColor = Color(255,180,1)
                ) {
                    Text("+", fontSize = 30.sp)
                }
            }
        }

        if (modalAddParticipantOpen){
            AddParticipantModal(context = context, projectViewModel = projectViewModel)
        }
    }

}

