package com.example.taskroom.ui.Screens.HomeScreen

import android.app.DatePickerDialog
import android.app.appsearch.AppSearchBatchResult
import android.content.Context
import android.widget.DatePicker
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Divider
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Assignment
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.DensitySmall
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Notes
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Title
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.taskroom.R
import com.example.taskroom.currentUser
import com.example.taskroom.data.localStorage.SessionStorage
import com.example.taskroom.data.remote.dto.UserDto
import com.example.taskroom.ui.util.AppScreens
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Calendar
import java.util.Date


//Modal open variable
var modalNewProjectOpen by mutableStateOf(false)
var modalNewProfileOpen by mutableStateOf(false)

@Composable
fun Homescreen(context:Context,nav: NavController,storage:SessionStorage,homeViewModel: HomeViewModel){

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
                items(currentUser.projects){ project ->
                    ProjectCard(title = project.title, desc = project.description, percent =100 , date =project.endDate )
                }
            }
        }
        else{
            LazyColumn {
                items(currentUser.tasks) { task ->
                    TaskCard(project = currentUser.projects.filter { o-> o.id == task.projectId }[0].title, task = task.title, state = task.status.toInt())
                }
            }
        }
       
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(20.dp), horizontalAlignment = Alignment.End, verticalArrangement = Arrangement.Bottom) {
        FloatingActionButton(
            onClick = {  modalNewProjectOpen = !modalNewProjectOpen},
            backgroundColor = Color(255,180,1)
        ) {
            Text("+", fontSize = 30.sp)
        }
    }

    if(modalNewProjectOpen){
        NewProjectModal(context,homeViewModel)
    }
    if(modalNewProfileOpen){
        ProfileViewModal(storage,nav)
    }
}

@Composable
fun TaskCard(project:String, task:String, state:Int){
    Card (modifier = Modifier.padding(10.dp)){
        Row (modifier = Modifier.padding(10.dp), verticalAlignment = Alignment.CenterVertically) {
            Checkbox(checked = false, onCheckedChange ={},modifier = Modifier.weight(1f), colors = CheckboxDefaults.colors() )
            Column (modifier = Modifier.weight(1f)){
                Text(text = task, fontSize = 20.sp, overflow = TextOverflow.Ellipsis)
                Text(text = project, overflow = TextOverflow.Ellipsis)
            }
            var color = Color.LightGray
            var text = ""
            if (state == 0 ) {
                color = Color(158,183,229)
                text="Started"
            }
            else if (state ==1 ){
                color = Color(204,41,54)
                text="Pending"
            }
            else if (state == 2){
                color = Color(86,227,159)
                text="Done"
            }
            else{
                color = Color.LightGray
                text="none"
            }

            Card (modifier = Modifier.weight(1f), backgroundColor =color ) {
                Text(text = "State: $text", fontWeight = FontWeight.Bold,modifier = Modifier.padding(3.dp))
            }
        }
    }

}

@Composable
fun ProjectCard(title:String,desc:String, percent:Int, date: String, ){
    Card (modifier = Modifier.padding(10.dp)) {
        Row(modifier = Modifier.padding(10.dp)) {
            Icon(imageVector = Icons.Default.Assignment, contentDescription = "Project Icon",modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .size(100.dp), )
            Column (modifier = Modifier
                .fillMaxWidth()
                .weight(2f)){
                Row (modifier = Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.End){
                    Text(text = date)
                }
                Text(text = title, fontWeight = FontWeight.Bold, fontSize = 20.sp, overflow = TextOverflow.Ellipsis)
                Text(text = desc, overflow = TextOverflow.Ellipsis)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "${percent}%")
                    LinearProgressIndicator(progress = percent.toFloat() / 100, color = Color.Black, backgroundColor = Color.Gray,modifier = Modifier.padding(2.dp))
                }

            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewProjectModal(context: Context,
                    homeViewModel: HomeViewModel
){
    androidx.compose.material3.AlertDialog(
        onDismissRequest = {
        }
    ) {

        Card {
            Column (modifier = Modifier.padding(10.dp)){
                Row (modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                    TextButton(onClick = { modalNewProjectOpen = !modalNewProjectOpen }) {
                        Text(text = "X", color = Color.Black, fontSize = 25.sp)
                    }
                }
                Spacer(modifier = Modifier.padding(top =10.dp))
                Row (modifier = Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.Center, ){
                    Text(text = "Create a new Project", fontSize = 25.sp)
                }

                Spacer(modifier = Modifier.padding(top =10.dp))
                TextField(
                    value = homeViewModel.title,
                    onValueChange = {homeViewModel.onTitlechange(it)},
                    isError = homeViewModel.titleError,
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color(255,180,1),
                        cursorColor =  Color(255,180,1),
                        unfocusedBorderColor = Color.White,
                        backgroundColor = Color.Transparent,
                        focusedLabelColor = Color.Black
                    ),
                    label = {
                            Text(text = "Title")
                    },
                    leadingIcon = {
                        Icon(imageVector = Icons.Default.Title, contentDescription ="" )
                    }
                )
                Spacer(modifier = Modifier.padding(top =10.dp))
                TextField(
                    value = homeViewModel.description,
                    onValueChange = {homeViewModel.onDescripcionchange(it)},
                    isError = homeViewModel.descriptionError,
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color(255,180,1),
                        cursorColor =  Color(255,180,1),
                        unfocusedBorderColor = Color.White,
                        backgroundColor = Color.Transparent,
                        focusedLabelColor = Color.Black
                    ),
                    label = {
                        Text(text = "Description")
                    },
                    leadingIcon = {
                        Icon(imageVector = Icons.Default.DensitySmall, contentDescription ="" )
                    }
                )
                Spacer(modifier = Modifier.padding(top =10.dp))
                TextField(
                    value = homeViewModel.projectNote,
                    onValueChange = {homeViewModel.onProjectNotehange(it)},
                    isError = homeViewModel.projectNoteError,
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color(255,180,1),
                        cursorColor =  Color(255,180,1),
                        unfocusedBorderColor = Color.White,
                        backgroundColor = Color.Transparent,
                        focusedLabelColor = Color.Black
                    ),
                    label = {
                        Text(text = "Note")
                    },
                    leadingIcon = {
                        Icon(imageVector = Icons.Default.Notes, contentDescription ="" )
                    }
                )
                Spacer(modifier = Modifier.padding(top =10.dp))
                DatePicker(context = context, homeViewModel = homeViewModel)
                Spacer(modifier = Modifier.padding(top =10.dp))
                Row (horizontalArrangement = Arrangement.Center){
                    Button(onClick = { modalNewProjectOpen = homeViewModel.addProject() },
                        modifier = Modifier
                            .fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color(255,180,1)
                        )
                    ) {
                        Row {
                            Text(text = "Done", fontSize = 20.sp)
                            Icon(imageVector = Icons.Default.Done, contentDescription ="Done" )
                        }

                    }
                }
            }
        }
        Divider(color = Color(255, 180, 1), thickness = 10.dp)
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePicker(
    context: Context,
    homeViewModel: HomeViewModel
) {

    val year: Int
    val month: Int
    val day: Int

    val calendar = Calendar.getInstance()
    year = calendar.get(Calendar.YEAR)
    month = calendar.get(Calendar.MONTH)
    day = calendar.get(Calendar.DAY_OF_MONTH)
    calendar.time = Date()


    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            homeViewModel.onEndDatechange( "$year-$month-$dayOfMonth")
        }, year, month, day
    )
    TextField(
        value =homeViewModel.endDate ,
        onValueChange = {homeViewModel.onEndDatechange(it) },
        readOnly = true,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color(255,180,1),
            cursorColor =  Color(255,180,1),
            unfocusedBorderColor = Color.White,
            backgroundColor = Color.Transparent,
            focusedLabelColor = Color.Black
        ),
        modifier = Modifier.fillMaxWidth(),
        isError = homeViewModel.endDateError,
        leadingIcon = { IconButton(onClick = {
            datePickerDialog.show()
        }) {
            Icon(imageVector = Icons.Filled.DateRange, contentDescription ="date" )
        }
        },
        label = { Text("End Date") },
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next)
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileViewModal(storage: SessionStorage, nav: NavController) {
    androidx.compose.material3.AlertDialog(
        onDismissRequest = {
        }
    ) {
        var corutine = rememberCoroutineScope()

        Card {
            Column(modifier = Modifier.padding(10.dp)) {
                Row (modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                    TextButton(onClick = { modalNewProfileOpen = !modalNewProfileOpen }) {
                        Text(text = "X", color = Color.Black, fontSize = 25.sp)
                    }

                }
                Spacer(modifier = Modifier.padding(top =10.dp))
                Row (modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                    Icon(imageVector = Icons.Default.AccountCircle, contentDescription = "",modifier = Modifier.size(60.dp))
                }

                Row(modifier= Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center) {
                    Text(text = "${currentUser.username}", color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 20.sp)
                    Text(text = "#${currentUser.id}", color = Color.Gray, fontWeight = FontWeight.Bold, fontSize = 20.sp)
                }
                Spacer(modifier = Modifier.padding(top =5.dp))
                Divider()
                Spacer(modifier = Modifier.padding(top =10.dp))
                Row (modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                    IconButton(onClick = {
                        corutine.launch {
                            modalNewProfileOpen = !modalNewProfileOpen
                            storage.saveID(0)
                            currentUser = UserDto()
                        }
                       nav.navigate(AppScreens.LoginScreen.route)
                    }) {
                        Column {

                            Icon(imageVector = Icons.Default.ExitToApp, contentDescription = "",modifier = Modifier.size(40.dp))
                            Text(text = "Log out", color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 10.sp)
                        }
                    }
                }
            }
        }
        Divider(color = Color(255, 180, 1), thickness = 10.dp)
    }
}

