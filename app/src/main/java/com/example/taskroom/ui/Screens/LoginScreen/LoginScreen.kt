package com.example.taskroom.ui.Screens.LoginScreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Key
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
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
import com.example.taskroom.R

@Composable
fun LoginScreen(){
    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    val background = painterResource(id = R.drawable.backgroundlogin)
    Image(painter = background, contentDescription ="Background",
        contentScale = ContentScale.FillBounds,
        modifier = Modifier
            .fillMaxSize() )
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(top = 150.dp, start = 50.dp,end=50.dp),
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
                modifier = Modifier.padding(top = 20.dp).fillMaxWidth()
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
                modifier = Modifier.fillMaxWidth().padding(top=20.dp).shadow(14.dp).shadow(14.dp ),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(238,240,242)
                )
            ) {

                Text(text = "LOG IN", fontSize = 20.sp)
            }


            Button(onClick = { /*TODO*/ },
                modifier = Modifier.fillMaxWidth().padding(top=250.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(255,180,1)
                )
            ) {

                Text(text = "SIGN IN", fontSize = 20.sp)
            }

    }
}