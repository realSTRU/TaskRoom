package com.example.taskroom

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.taskroom.ui.Screens.HomeScreen.Homescreen
import com.example.taskroom.ui.Screens.LoginScreen.LoginScreen
import com.example.taskroom.ui.theme.TaskRoomTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TaskRoomTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(20,20,20)
                ) {

                    Homescreen(this)
                }
            }
        }
    }
}

