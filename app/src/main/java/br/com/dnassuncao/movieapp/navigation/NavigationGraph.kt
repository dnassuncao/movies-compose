package br.com.dnassuncao.movieapp.navigation

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import br.com.dnassuncao.movieapp.features.movie_popular.presentation.MoviePopularScreen
import br.com.dnassuncao.movieapp.features.movie_popular.presentation.MoviePopularViewModel
import br.com.dnassuncao.movieapp.features.search_movie.presentantion.MovieSearchScreen
import br.com.dnassuncao.movieapp.features.search_movie.presentantion.MovieSearchViewModel

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomNavItem.MoviePopular.route
    ) {
        composable(BottomNavItem.MoviePopular.route) {

            val viewModel: MoviePopularViewModel = hiltViewModel()
            val uiState = viewModel.uiState.collectAsStateWithLifecycle().value

            MoviePopularScreen(
                uiState = uiState,
                navigateToDetailMovie = { }
            )
        }
        composable(BottomNavItem.MovieSearch.route) {

            val viewModel: MovieSearchViewModel = hiltViewModel()
            val uiState = viewModel.uiState.collectAsStateWithLifecycle().value

            MovieSearchScreen(
                uiState = uiState,
                onEvent = {
                    viewModel.event(it)
                },
                onFetch = {
                    viewModel.searchMovie(it)
                },
                navigateToDetailMovie = { }
            )

        }
        composable(BottomNavItem.MovieFavorite.route) {
            Text(text = "MovieFavorite")
        }
    }
}