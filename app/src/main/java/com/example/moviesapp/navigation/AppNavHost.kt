package com.example.moviesapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.moviesapp.R
import com.example.moviesapp.data.Movie
import com.example.moviesapp.ui.screens.DetailScreen
import com.example.moviesapp.ui.screens.HomeScreen
import com.example.moviesapp.ui.screens.LoginScreen
import com.example.moviesapp.utilities.NavigationItem

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier, // The modifier to be applied to the layout
    navController: NavHostController, // The navController for this host
    startDestination: String = NavigationItem.Login.route // Start route
) {
    NavHost( // Provides in place in the Compose hierarchy for self contained navigation to occur.
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable( // This method adds the composable to the NavGraphBuilder
            route = NavigationItem.Login.route // Route for the destination
        ) {
            LoginScreen(navController) // Composable for the destination
        }

        composable( // This method adds the composable to the NavGraphBuilder
            route = NavigationItem.Home.route // Route for the destination
        ) {
            HomeScreen(navController) // Composable for the destination
        }

        composable( // This method adds the composable to the NavGraphBuilder
            route = NavigationItem.Detail.route + "/{movieName}/{movieImage}", // Route for the destination that receives the name and image as arguments
            arguments = listOf( // List of arguments passed by navigation
                navArgument(name="movieName") { type = NavType.StringType }, // Movie name by argument
                navArgument(name = "movieImage") { type = NavType.ReferenceType } // Drawable reference
            )
        ) {
            val image = it.arguments?.getInt("movieImage", R.drawable.no_image_available) ?: R.drawable.no_image_available // Get the image by argument
            val name = it.arguments?.getString("movieName", "") ?: "" // Get the name by argument

            // Create a new movie object
            val movie = Movie(name = name, drawable = image)

            DetailScreen(movie = movie, navController) // Composable for the destination, this composable receives a movie object
        }
    }
}