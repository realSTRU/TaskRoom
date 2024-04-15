package com.example.taskroom.ui.Screens.ProjectScreen

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.DropdownMenu
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Notes
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material.icons.filled.Title
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import com.example.taskroom.ui.Screens.HomeScreen.modalAddParticipantOpen
import com.example.taskroom.ui.Screens.HomeScreen.modalAddTaskOpen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTaskModal(context: Context,
                 projectViewModel: ProjectViewModel
) {
    androidx.compose.material3.AlertDialog(
        onDismissRequest = {
        }
    ) {
        var expanded by remember { mutableStateOf(false) }

        var selectedText by remember { mutableStateOf("") }
        var mTextFieldSize by remember { mutableStateOf(Size.Zero) }

        Card {
            Column(modifier = Modifier.padding(10.dp)) {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                    TextButton(onClick = { modalAddTaskOpen = !modalAddTaskOpen }) {
                        Text(text = "X", color = Color.Black, fontSize = 25.sp)
                    }
                }
                Spacer(modifier = Modifier.padding(top = 10.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Text(text = "Add Task", fontSize = 25.sp)
                }
                TextField(
                    value = projectViewModel.taskTitle,
                    onValueChange = { projectViewModel.onTaskTitleChange(it) },
                    isError = projectViewModel.taskTitleError,
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color(255, 180, 1),
                        cursorColor = Color(255, 180, 1),
                        unfocusedBorderColor = Color.White,
                        backgroundColor = Color.Transparent,
                        focusedLabelColor = Color.Black
                    ),
                    label = {
                        Text(text = "Title")
                    },
                    leadingIcon = {
                        Icon(imageVector = Icons.Default.Title, contentDescription = "")
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    )
                )
                Spacer(modifier = Modifier.padding(top = 10.dp))
                TextField(
                    value = projectViewModel.taskDescripcion,
                    onValueChange = { projectViewModel.onTaskDescriptionChange(it) },
                    isError = projectViewModel.taskDescripcionError,
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color(255, 180, 1),
                        cursorColor = Color(255, 180, 1),
                        unfocusedBorderColor = Color.White,
                        backgroundColor = Color.Transparent,
                        focusedLabelColor = Color.Black
                    ),
                    label = {
                        Text(text = "Description")
                    },
                    leadingIcon = {
                        Icon(imageVector = Icons.Default.Description, contentDescription = "")
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    )
                )
                Spacer(modifier = Modifier.padding(top = 10.dp))
                TextField(
                    value = projectViewModel.taskNote,
                    onValueChange = { projectViewModel.onTaskNoteChange(it) },
                    isError = projectViewModel.taskNoteError,
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color(255, 180, 1),
                        cursorColor = Color(255, 180, 1),
                        unfocusedBorderColor = Color.White,
                        backgroundColor = Color.Transparent,
                        focusedLabelColor = Color.Black
                    ),
                    label = {
                        Text(text = "Note")
                    },
                    leadingIcon = {
                        Icon(imageVector = Icons.Default.Notes, contentDescription = "")
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    )
                )

                Spacer(modifier = Modifier.padding(top = 10.dp))
                Column {
                    TextField(
                        value = selectedText,
                        onValueChange = { },
                        isError = projectViewModel.taskUserIdError,
                        readOnly = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .onGloballyPositioned { coordinates ->
                                mTextFieldSize = coordinates.size.toSize()
                            },
                        label = { Text("Select participant to assing") },
                        trailingIcon = {
                            Icon(
                                Icons.Default.PersonAdd, "contentDescription",
                                Modifier.clickable { expanded = !expanded })
                        },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Color(255, 180, 1),
                            cursorColor = Color(255, 180, 1),
                            unfocusedBorderColor = Color.White,
                            backgroundColor = Color.Transparent,
                            focusedLabelColor = Color.Black)
                    )
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false },
                        modifier = Modifier
                            .width(with(LocalDensity.current) { mTextFieldSize.width.toDp() })
                    ) {
                        projectViewModel.roleUserList.forEach { user ->
                            DropdownMenuItem(
                                text = { Text(text = " ${user.user.name} ${user.user.surname} || ${user.user.username}") },
                                onClick = {
                                    projectViewModel.onTaskUserIdChange("${user.user.id}")
                                    selectedText = " ${user.user.name} ${user.user.surname} "
                                    expanded = !expanded
                                })
                        }
                    }
                }
                Spacer(modifier = Modifier.padding(top = 10.dp))
                ProjectDatePicker(context = context, projectViewModel = projectViewModel)
                Spacer(modifier = Modifier.padding(top = 10.dp))
                Row(horizontalArrangement = Arrangement.Center) {
                    Button(
                        onClick = { projectViewModel.addTask() },
                        modifier = Modifier
                            .fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color(255, 180, 1)
                        )
                    ) {
                        Row {
                            Text(text = "Done", fontSize = 20.sp)
                            Icon(imageVector = Icons.Default.Done, contentDescription = "Add")
                        }

                    }
                }
            }
        }
    }
}