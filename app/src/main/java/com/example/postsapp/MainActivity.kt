package com.example.postsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.postsapp.ui.theme.AnotationAppTheme
import com.example.postsapp.ui.theme.register.UserRegistration
import com.example.postsapp.ui.theme.register.UserRegistrationScreen
import com.example.postsapp.ui.theme.post.CreatePage
import com.example.postsapp.ui.theme.post.CreatePostPage
import com.example.postsapp.ui.theme.detail.DetailsPage
import com.example.postsapp.ui.theme.home.HomePage
import com.example.postsapp.ui.theme.login.LoginPage
import com.example.postsapp.ui.theme.detail.DetailsViewModel
import com.google.firebase.auth.FirebaseAuth

@Suppress("IMPLICIT_CAST_TO_ANY")
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AnotationAppTheme {
                val navController = rememberNavController()
                val user = FirebaseAuth.getInstance().currentUser
                val startDestination = if (user != null) HomePage else LoginPage
                NavHost(
                    navController = navController,
                    startDestination = startDestination
                ) {
                    composable<UserRegistration> {
                        UserRegistrationScreen(
                            onLoginBack = { navController.navigate(LoginPage) }
                        )
                    }
                    composable<CreatePage> {
                        CreatePostPage(
                            onNavigateBack = { navController.navigate(HomePage) }
                        )
                    }
                    composable<DetailsPage> {
                        val postId = it.toRoute<DetailsPage>().id
                        DetailsPage(DetailsViewModel(postId))
                    }
                    composable<HomePage> {
                        HomePage(
                            onAddPost = { navController.navigate(CreatePage) },
                            onPostClick = { id -> navController.navigate(DetailsPage(id)) }
                        )
                    }
                    composable<LoginPage> {
                        LoginPage(
                            login = { navController.navigate(HomePage) },
                            onRegisterClick = { navController.navigate(UserRegistration) }
                        )
                    }
                }
            }
        }
    }
}

