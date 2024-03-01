package br.com.dnassuncao.movieapp.features.search_movie.presentantion

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import br.com.dnassuncao.movieapp.features.search_movie.domain.usecase.GetSearchMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MovieSearchViewModel @Inject constructor(
    private val getSearchUseCase: GetSearchMoviesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(MovieSearchState())
    val uiState: StateFlow<MovieSearchState> = _uiState.asStateFlow()

    fun searchMovie(query: String = "") {
        val movies = getSearchUseCase.invoke(
            params = GetSearchMoviesUseCase.Params(query)
        ).cachedIn(viewModelScope)

        _uiState.update { currentState ->
            currentState.copy(
                movies = movies
            )
        }
    }

    fun event(event: MovieSearchEvent) {
        when(event) {
            is MovieSearchEvent.EnteredQuery -> {
                _uiState.update { currentState ->
                    currentState.copy(
                        query = event.value
                    )
                }
            }
        }
    }
}