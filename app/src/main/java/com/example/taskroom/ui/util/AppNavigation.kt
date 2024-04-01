package com.example.taskroom.ui.util

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.taskroom.data.localStorage.SessionStorage
import com.example.taskroom.ui.Screens.HomeScreen.HomeViewModel
import com.example.taskroom.ui.Screens.HomeScreen.Homescreen
import com.example.taskroom.ui.Screens.LoginScreen.LoginScreen
import com.example.taskroom.ui.Screens.LoginScreen.LoginViewModel
import com.example.taskroom.ui.Screens.LoginScreen.SigninViewModel
import com.example.taskroom.ui.Screens.SplashScreen.SplashScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun AppNavigation(
    context: Context,
    loginViewModel: LoginViewModel = hiltViewModel(),
    signinViewModel: SigninViewModel  = hiltViewModel(),
    homeViewModel : HomeViewModel = hiltViewModel()

)
{
    val storage : SessionStorage = SessionStorage(context)

    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = AppScreens.SplashScreen.route
    ) {
        composable(AppScreens.SplashScreen.route) {
            SplashScreen(nav = navController,storage=storage)
        }
        composable(AppScreens.HomeScreen.route) {
            Homescreen(context=context,nav = navController,storage=storage,homeViewModel=homeViewModel)
        }
        composable(AppScreens.LoginScreen.route) {
            LoginScreen(nav = navController, loginViewModel=loginViewModel, signinViewModel = signinViewModel,storage=storage)
        }


    }
}