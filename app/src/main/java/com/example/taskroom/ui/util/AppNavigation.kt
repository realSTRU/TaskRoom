package com.example.taskroom.ui.util

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.taskroom.data.localStorage.SessionStorage
import com.example.taskroom.ui.Screens.HomeScreen.Homescreen
import com.example.taskroom.ui.Screens.LoginScreen.LoginScreen
import com.example.taskroom.ui.Screens.SplashScreen.SplashScreen

@Composable
fun AppNavigation(
    context: Context,
)
{
    val storage : SessionStorage = SessionStorage(context)
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = AppScreens.SplashScreen.route
    ) {
        composable(AppScreens.SplashScreen.route) {
            SplashScreen(nav = navController)
        }
        composable(AppScreens.HomeScreen.route) {
            Homescreen(context=context,nav = navController)
        }
        composable(AppScreens.LoginScreen.route) {
            LoginScreen(nav = navController)
        }


    }
}