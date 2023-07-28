package com.flickr.app.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.flickr.app.ui.home.HomeScreen
import com.flickr.app.ui.photoDetail.ImageDetailScreen
import com.flickr.app.ui.userProfile.UserProfileScreen
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object UserProfile : Screen("user/{userId}") {
        fun createRoute(userId: String) = "user/$userId"
    }

    object PhotoDetailScreen : Screen("photoDetail/{photoId}") {
        fun createRoute(photoId: String) = "photoDetail/$photoId"
    }
}

@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@Composable
fun FlickrNavHost(
    navController: NavHostController = rememberAnimatedNavController()
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
    ) { innerPadding ->

        AnimatedNavHost(
            navController = navController,
            startDestination = Screen.Home.route
        ) {

            composable(
                route = Screen.Home.route
            ) {
                HomeScreen(
                    onImageSelected = {
                        navController.navigate(Screen.PhotoDetailScreen.createRoute(it))
                    },
                    onUserSelected = {
                        navController.navigate(Screen.UserProfile.createRoute(it))
                    }
                )
            }

            composable(
                route = Screen.UserProfile.route
            ) { navBackStackEntry ->
                val userId = navBackStackEntry.arguments?.getString("userId")
                requireNotNull(userId) { "userId cannot be null" }

                UserProfileScreen(
                    userId = userId,
                    onBackPressed = {
                        navController.popBackStack()
                    }
                )
            }

            composable(
                route = Screen.PhotoDetailScreen.route
            ) { navBackStackEntry ->
                val photoId = navBackStackEntry.arguments?.getString("photoId")
                requireNotNull(photoId) { "photoId cannot be null" }

                ImageDetailScreen(
                    photoId = photoId,
                    onBackPressed = {
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}