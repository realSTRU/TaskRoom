package com.example.taskroom.ui.Screens.SplashScreen

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Build
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import coil.size.Size
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

   ChargerScreenScreen()

}

@Composable
fun ChargerScreenScreen(){

    Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center, modifier = Modifier.fillMaxSize()) {
        val context = LocalContext.current
        val imageLoader = ImageLoader.Builder(context)
            .components {
                if (Build.VERSION.SDK_INT >= 28) {
                    add(ImageDecoderDecoder.Factory())
                } else {
                    add(GifDecoder.Factory())
                }
            }
            .build()
        Image(
            painter = rememberAsyncImagePainter(
                ImageRequest.Builder(context).data(data = R.drawable.gifcharge).apply(block = {
                    size(Size.ORIGINAL)
                }).build(), imageLoader = imageLoader
            ),
            contentScale = ContentScale.FillWidth,
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
        )

    }
}