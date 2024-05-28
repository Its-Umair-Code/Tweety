package com.revealquran.tweety

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.revealquran.tweety.screens.CategoryScreen
import com.revealquran.tweety.screens.DetailScreen
import com.revealquran.tweety.ui.theme.TweetyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

//    @Inject
//    lateinit var tweetsyApi: TweetsyApi

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // enableEdgeToEdge()

        /*GlobalScope.launch {
            var respose = tweetsyApi.getCategories()
            Log.d("Data Check", respose.body()?.distinct().toString())
        }*/



        setContent {
            TweetyTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(text = "Tweety")
                            },
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = Color.Black,
                                titleContentColor = Color.White
                            )
                        )
                    },
                    content = {
                        Box(modifier = Modifier.padding(it)) {
                            App()
                        }
                    }
                )
            }
        }
    }

    @Composable
    fun App() {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = "category") {
            composable(route = "category") {
                CategoryScreen() {
                    navController.navigate("detail/$it")
                }
            }
            composable(route = "detail/{category}", arguments = listOf(
                navArgument(name = "category") {
                    type = NavType.StringType
                }
            )) {
                DetailScreen()
            }
        }

    }
}