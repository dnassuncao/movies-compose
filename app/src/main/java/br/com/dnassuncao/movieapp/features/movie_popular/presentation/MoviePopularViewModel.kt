package br.com.dnassuncao.movieapp.features.movie_popular.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import br.com.dnassuncao.movieapp.features.movie_popular.domain.usecase.GetPopularMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviePopularViewModel @Inject constructor(
    private val getPopularUseCase: GetPopularMoviesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(MoviePopularState())
    val uiState: StateFlow<MoviePopularState> = _uiState.asStateFlow()

    init {
        loadPopularMovies()
    }

    private fun loadPopularMovies() {
        val movies = getPopularUseCase.invoke().cachedIn(viewModelScope)
        _uiState.update { currentState ->
            currentState.copy(
                movies = movies
            )
        }
    }
}