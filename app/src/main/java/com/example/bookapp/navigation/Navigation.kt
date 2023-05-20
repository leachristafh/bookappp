package com.example.bookapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.bookapp.screens.HomeScreen
import com.example.bookapp.screens.*

import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bookapp.viewmodels.EditViewModel


@Composable


fun Navigation() { //früher : navController: NavHostController


    val navController = rememberNavController()

// inside a composable SOLLTE ES EIG BOOKS SEIN WIE MEIN LEON???
    // val editViewModel: EditViewModel = viewModel()   val movieViewModel: MoviesViewModel = viewModel()
 //bei leon ist es  Screen.MainScreen.route bei der start destination
    NavHost(navController, startDestination = Screen.HomeScreen.route) {
        composable(route = Screen.HomeScreen.route) {
            HomeScreen(navController = navController)

        }
        composable(
            Screen.EditScreen.route,
            arguments = listOf(navArgument(name = DETAIL_ARGUMENT_KEY) {
                type = NavType.StringType
            })
        )
        { backStackEntry ->    // backstack contains all information from navhost
            EditScreen( //beim Import passiert folgendes: Screen.Editscreen
                navController = navController,

                bookId = backStackEntry.arguments?.getString(DETAIL_ARGUMENT_KEY)
            )   // get the argument from navhost that will be passed
        }
    }
}

