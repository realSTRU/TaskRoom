package com.example.taskroom.ui.Screens.LoginScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Key
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Title
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.taskroom.R


var modalSigninOpen by mutableStateOf(false)

@Composable
fun LoginScreen(nav : NavController){
    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    val background = painterResource(id = R.drawable.backgroundlogin)
    Image(painter = background, contentDescription ="Background",
        contentScale = ContentScale.FillBounds,
        modifier = Modifier
            .fillMaxSize() )
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(top = 150.dp, start = 50.dp, end = 50.dp),
        horizontalAlignment = Alignment.CenterHorizontally
        ) {

            //Username
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "USERNAME", color = Color(238,240,242), fontWeight = FontWeight.Light, fontSize = 20.sp)
                TextField(
                    value = "",
                    onValueChange = {},
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color(255,180,1),
                        cursorColor =  Color(255,180,1),
                        unfocusedBorderColor = Color.White,
                        backgroundColor = Color(158,183,229,60),
                    ),
                    leadingIcon = {
                        Icon(imageVector = Icons.Default.Person, contentDescription ="" )
                    }
                )

            }

            //PASSWORD
            Column(
                modifier = Modifier
                    .padding(top = 20.dp)
                    .fillMaxWidth()
            ) {
                Text(text = "PASSWORD", color = Color(238,240,242), fontWeight = FontWeight.Light, fontSize = 20.sp)
                TextField(
                    value = "adadasdasdsa",
                    onValueChange = {},
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color(255,180,1),
                        cursorColor =  Color(255,180,1),
                        unfocusedBorderColor = Color.White,
                        backgroundColor = Color(158,183,229,60),
                    ),
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    trailingIcon = {
                        val image = if (passwordVisible)
                            Icons.Filled.Visibility
                        else Icons.Filled.VisibilityOff
                        val description = if (passwordVisible) "Hide password" else "Show password"
                        IconButton(onClick = {passwordVisible = !passwordVisible}){
                            Icon(imageVector  = image, description)
                        }
                    },
                    leadingIcon = {
                        Icon(imageVector = Icons.Default.Key, contentDescription ="" )
                    }
                )
            }

            Button(onClick = { /*TODO*/ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp)
                    .shadow(14.dp)
                    .shadow(14.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(238,240,242)
                )
            ) {

                Text(text = "LOG IN", fontSize = 20.sp)
            }


            Button(onClick = { modalSigninOpen = !modalSigninOpen },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 250.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(255,180,1)
                )
            ) {

                Text(text = "SIGN IN", fontSize = 20.sp)
            }
        if (modalSigninOpen){
            SignInModal()
        }

    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInModal() {
    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    androidx.compose.material3.AlertDialog(
        onDismissRequest = {
        }
    ) {

        Card {
            Column(modifier = Modifier.padding(10.dp)) {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                    TextButton(onClick = { modalSigninOpen = !modalSigninOpen }) {
                        Text(text = "X", color = Color.Black, fontSize = 25.sp)
                    }
                }
                Spacer(modifier = Modifier.padding(top = 10.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Text(text = "SIGN IN", fontSize = 25.sp)
                }
                Spacer(modifier = Modifier.padding(top =10.dp))
                TextField(
                    value = "",
                    onValueChange = {},
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color(255,180,1),
                        cursorColor =  Color(255,180,1),
                        unfocusedBorderColor = Color.White,
                        backgroundColor = Color.Transparent,
                        focusedLabelColor = Color.Black
                    ),
                    label = {
                        Text(text = "Username")
                    },
                    leadingIcon = {
                        Icon(imageVector = Icons.Default.Person, contentDescription ="" )
                    }
                )
                Spacer(modifier = Modifier.padding(top =10.dp))
                TextField(
                    value = "",
                    onValueChange = {},
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color(255,180,1),
                        cursorColor =  Color(255,180,1),
                        unfocusedBorderColor = Color.White,
                        backgroundColor = Color.Transparent,
                        focusedLabelColor = Color.Black
                    ),
                    label = {
                        Text(text = "Email")
                    },
                    leadingIcon = {
                        Icon(imageVector = Icons.Default.Email, contentDescription ="" )
                    }
                )
                Spacer(modifier = Modifier.padding(top =10.dp))
                TextField(
                    value = "",
                    onValueChange = {},
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color(255,180,1),
                        cursorColor =  Color(255,180,1),
                        unfocusedBorderColor = Color.White,
                        backgroundColor = Color.Transparent,
                        focusedLabelColor = Color.Black
                    ),
                    label = {
                        Text(text = "Username")
                    },
                    leadingIcon = {
                        Icon(imageVector = Icons.Default.Person, contentDescription ="" )
                    }
                )
                Spacer(modifier = Modifier.padding(top =10.dp))
                TextField(
                    value = "",
                    onValueChange = {},
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color(255,180,1),
                        cursorColor =  Color(255,180,1),
                        unfocusedBorderColor = Color.White,
                        backgroundColor = Color.Transparent,
                        focusedLabelColor = Color.Black
                    ),
                    label = {
                        Text(text = "Name")
                    },
                    leadingIcon = {
                        Icon(imageVector = Icons.Default.Title, contentDescription ="" )
                    }
                )
                Spacer(modifier = Modifier.padding(top =10.dp))
                TextField(
                    value = "",
                    onValueChange = {},
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color(255,180,1),
                        cursorColor =  Color(255,180,1),
                        unfocusedBorderColor = Color.White,
                        backgroundColor = Color.Transparent,
                        focusedLabelColor = Color.Black
                    ),
                    label = {
                        Text(text = "Surname")
                    },
                    leadingIcon = {
                        Icon(imageVector = Icons.Default.Title, contentDescription ="" )
                    }
                )
                Spacer(modifier = Modifier.padding(top =10.dp))
                TextField(
                    value = "",
                    onValueChange = {},
                    label = {
                        Text(text = "Password")
                    },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color(255,180,1),
                        cursorColor =  Color(255,180,1),
                        unfocusedBorderColor = Color.White,
                        backgroundColor = Color.Transparent,
                    ),
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),

                    trailingIcon = {
                        val image = if (passwordVisible)
                            Icons.Filled.Visibility
                        else Icons.Filled.VisibilityOff
                        val description = if (passwordVisible) "Hide password" else "Show password"
                        IconButton(onClick = {passwordVisible = !passwordVisible}){
                            Icon(imageVector  = image, description)
                        }
                    },
                    leadingIcon = {
                        Icon(imageVector = Icons.Default.Key, contentDescription ="" )
                    }
                )
                Spacer(modifier = Modifier.padding(top =10.dp))
                TextField(
                    value = "",
                    onValueChange = {},
                    label = {
                        Text(text = "Repeat Password")
                    },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color(255,180,1),
                        cursorColor =  Color(255,180,1),
                        unfocusedBorderColor = Color.White,
                        backgroundColor = Color.Transparent,
                    ),
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    leadingIcon = {
                        Icon(imageVector = Icons.Default.Key, contentDescription ="" )
                    }
                )
                Spacer(modifier = Modifier.padding(top =10.dp))
                Row (horizontalArrangement = Arrangement.Center){
                    Button(onClick = { /*TODO*/ },
                        modifier = Modifier
                            .fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color(255,180,1)
                        )
                    ) {
                        Row {
                            Text(text = "Register", fontSize = 20.sp)
                            Icon(imageVector = Icons.Default.Done, contentDescription ="Done" )
                        }

                    }
                }
            }
        }

        Divider(color= Color(255,180,1), thickness = 10.dp)
    }
}