package com.nimesh.vasani.speer_technologies_android.presentation.viewmodels


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nimesh.vasani.speer_technologies_android.data.model.GitHubUserResponse
import com.nimesh.vasani.speer_technologies_android.data.model.User
import com.nimesh.vasani.speer_technologies_android.data.repositories.AuthRepository
import com.nimesh.vasani.speer_technologies_android.others.Response
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModel(
    private val repository: AuthRepository
) : ViewModel() {

    private val _searchResults = MutableStateFlow<Response<GitHubUserResponse>?>(null)
    val searchResults: StateFlow<Response<GitHubUserResponse>?> = _searchResults

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    fun searchUsers(query: String) {
        viewModelScope.launch {
            _isLoading.value = true
            repository.searchGitHubUsers(query).collect { response ->
                _isLoading.value = false
                if (response.data!=null && response.data.items.isNotEmpty()) {
                    _searchResults.value = response
                    _errorMessage.value = null
                } else {
                    _errorMessage.value = "Error: ${response.message}"
                    Log.e("AuthViewModel", "Error: ${response.message}")
                }
            }
        }
    }

}
