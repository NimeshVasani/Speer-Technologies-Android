package com.nimesh.vasani.speer_technologies_android.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nimesh.vasani.speer_technologies_android.data.model.SearchUiState
import com.nimesh.vasani.speer_technologies_android.data.repositories.SearchRepository
import com.nimesh.vasani.speer_technologies_android.others.Response
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SearchViewModel(
    private val searchRepository: SearchRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState: StateFlow<SearchUiState> = _uiState.asStateFlow()


    fun getAllPosts() {
        _uiState.value = _uiState.value.copy(isLoading = true)
        viewModelScope.launch {
            val postResponse = searchRepository.getAllPosts()
            when(postResponse){
                is Response.Success -> {
                    _uiState.value = _uiState.value.copy(posts = postResponse.data?.posts?:emptyList(), isLoading = false)
                }
                is Response.Error -> {
                    _uiState.value = _uiState.value.copy(error = postResponse.message,isLoading = false)
                }
                is Response.Loading -> {
                    _uiState.value = _uiState.value.copy(isLoading = true)

                }
            }
        }
    }

}