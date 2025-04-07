package com.nimesh.vasani.speer_technologies_android.presentation.viewmodels


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nimesh.vasani.speer_technologies_android.data.model.GitHubUserResponse
import com.nimesh.vasani.speer_technologies_android.data.model.User
import com.nimesh.vasani.speer_technologies_android.data.repositories.UsersRepository
import com.nimesh.vasani.speer_technologies_android.others.Response
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UsersViewmodel(
    private val repository: UsersRepository
) : ViewModel() {

    private val _searchResults = MutableStateFlow<Response<GitHubUserResponse>?>(null)
    val searchResults: StateFlow<Response<GitHubUserResponse>?> = _searchResults

    private val _currentUser = MutableStateFlow<User?>(null)
    val currentUser: StateFlow<User?> = _currentUser.asStateFlow()

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

    fun setCurrentUser(user: User){
        _currentUser.value = user
    }


}
