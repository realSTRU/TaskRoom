package com.example.taskroom.ui.Screens.ProjectScreen

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Numbers
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Title
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taskroom.ui.Screens.HomeScreen.modalAddParticipantOpen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddParticipantModal(context: Context,
                    projectViewModel: ProjectViewModel
) {
    androidx.compose.material3.AlertDialog(
        onDismissRequest = {
        }
    ) {
        Card {
            Column(modifier = Modifier.padding(10.dp)) {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                    TextButton(onClick = { modalAddParticipantOpen = !modalAddParticipantOpen }) {
                        Text(text = "X", color = Color.Black, fontSize = 25.sp)
                    }
                }
                Spacer(modifier = Modifier.padding(top = 10.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Text(text = "Add Participant", fontSize = 25.sp)
                }

                Spacer(modifier = Modifier.padding(top = 10.dp))
                TextField(
                    value = projectViewModel.username,
                    onValueChange = { projectViewModel.onUsernameChange(it) },
                    isError = projectViewModel.usernameError,
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color(255, 180, 1),
                        cursorColor = Color(255, 180, 1),
                        unfocusedBorderColor = Color.White,
                        backgroundColor = Color.Transparent,
                        focusedLabelColor = Color.Black
                    ),
                    label = {
                        Text(text = "Username")
                    },
                    leadingIcon = {
                        Icon(imageVector = Icons.Default.Person, contentDescription = "")
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    )
                )
                Spacer(modifier = Modifier.padding(top = 10.dp))
                TextField(
                    value = projectViewModel.userid,
                    onValueChange = { projectViewModel.onUserIdChange(it) },
                    isError = projectViewModel.usernameError,
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color(255, 180, 1),
                        cursorColor = Color(255, 180, 1),
                        unfocusedBorderColor = Color.White,
                        backgroundColor = Color.Transparent,
                        focusedLabelColor = Color.Black
                    ),
                    label = {
                        Text(text = "ID")
                    },
                    leadingIcon = {
                        Icon(imageVector = Icons.Default.Numbers, contentDescription = "")
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    )
                )
                Spacer(modifier = Modifier.padding(top = 10.dp))
                Row(horizontalArrangement = Arrangement.Center) {
                    Button(
                        onClick = { projectViewModel.addParticipant() },
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