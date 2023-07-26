package com.flickr.app

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import com.flickr.app.navigation.FlickrNavHost
import com.flickr.app.ui.theme.FlickrAppTheme

@ExperimentalAnimationApi
@ExperimentalMaterial3Api
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            FlickrAppTheme {
                FlickrNavHost()
            }
        }
    }
}