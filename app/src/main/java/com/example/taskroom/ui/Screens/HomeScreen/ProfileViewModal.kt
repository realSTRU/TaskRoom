package com.example.taskroom.ui.Screens.HomeScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.taskroom.currentUser
import com.example.taskroom.data.localStorage.SessionStorage
import com.example.taskroom.data.remote.dto.UserDto
import com.example.taskroom.ui.util.AppScreens
import kotlinx.coroutines.launch

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
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                    TextButton(onClick = { modalNewProfileOpen = !modalNewProfileOpen }) {
                        Text(text = "X", color = Color.Black, fontSize = 25.sp)
                    }

                }
                Spacer(modifier = Modifier.padding(top = 10.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = "",
                        modifier = Modifier.size(60.dp)
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "${currentUser.username}",
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                    Text(
                        text = "#${currentUser.id}",
                        color = Color.Gray,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                }
                Spacer(modifier = Modifier.padding(top = 5.dp))
                Divider()
                Spacer(modifier = Modifier.padding(top = 10.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    IconButton(onClick = {
                        corutine.launch {
                            modalNewProfileOpen = !modalNewProfileOpen
                            storage.saveID(0)
                        }
                        currentUser = UserDto()
                        nav.navigate(AppScreens.LoginScreen.route)
                    }) {
                        Column {

                            Icon(
                                imageVector = Icons.Default.ExitToApp,
                                contentDescription = "",
                                modifier = Modifier.size(40.dp)
                            )
                            Text(
                                text = "Log out",
                                color = Color.Black,
                                fontWeight = FontWeight.Bold,
                                fontSize = 10.sp
                            )
                        }
                    }
                }
            }
        }
        Divider(color = Color(255, 180, 1), thickness = 10.dp)
    }
}