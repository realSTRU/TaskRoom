package com.example.taskroom.ui.util

sealed class AppScreens(val route : String)
{
    object SplashScreen: AppScreens("splash_Screen")
    object HomeScreen: AppScreens("home_Screen")
    object LoginScreen: AppScreens("login_Screen")
    object SigninScreen: AppScreens("Signin_Screen")
    object ProjectScreen: AppScreens("project_Screen")
    object TasksScreen: AppScreens("tasks_Screen")
}