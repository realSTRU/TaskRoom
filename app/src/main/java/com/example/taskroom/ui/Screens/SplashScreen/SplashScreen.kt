package com.example.taskroom.ui.Screens.SplashScreen

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.taskroom.R
import com.example.taskroom.data.localStorage.SessionStorage
import com.example.taskroom.ui.util.AppScreens
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch

@Composable
fun SplashScreen(
    nav : NavController,
    storage: SessionStorage

) {
    var corutine = rememberCoroutineScope()
    LaunchedEffect(key1 = true) {
        delay(3000)
        nav.navigate(AppScreens.LoginScreen.route)

    }

    val background = painterResource(id = R.drawable.splashscreen)
    Image(painter = background,
        contentDescription ="Background",
        contentScale = ContentScale.FillBounds,
        modifier = Modifier.fillMaxSize() )

}