package com.nimesh.vasani.speer_technologies_android.presentation.viewmodels


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.database.core.Repo
import com.nimesh.vasani.speer_technologies_android.data.model.GitHubUserResponse
import com.nimesh.vasani.speer_technologies_android.data.model.User
import com.nimesh.vasani.speer_technologies_android.data.repositories.UsersRepository
import com.nimesh.vasani.speer_technologies_android.others.Response
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
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
            repository.searchGitHubUsers(query).collectLatest { response ->
                _isLoading.value = false
                if (response.data != null && response.data.items.isNotEmpty()) {
                    _searchResults.value = response
                    _errorMessage.value = null
                } else {
                    _errorMessage.value = "Error: ${response.message}"
                    Log.e("AuthViewModel", "Error: ${response.message}")
                }
            }
        }
    }

    fun setCurrentUser(user: User) {
        viewModelScope.launch {
            _currentUser.value = user
            Log.d("UsersViewmodel", "setCurrentUser: $user")
        }
    }

    fun getFollowers(link: String) {
        viewModelScope.launch {
            _isLoading.value = true
            repository.getFollowersFollowing(link).collect { response ->
                _isLoading.value = false
                if (response.data != null) {
                    Log.e("UsersViewmodel", "Error: ${response.message}")

                    _currentUser.value = _currentUser.value?.copy(followers = response.data)
                    _errorMessage.value = null
                }
                else {
                    _errorMessage.value = "Error: ${response.message}"
                    Log.e("UsersViewmodel", "Error: ${response.message}")
                }
            }
        }
    }
    fun getFollowing(link: String) {
        viewModelScope.launch {
            _isLoading.value = true
            repository.getFollowersFollowing(link).collect { response ->
                _isLoading.value = false
                if (response.data != null) {
                    _currentUser.value = _currentUser.value?.copy(following = response.data)
                    _errorMessage.value = null
                    Log.e("UsersViewmodel", "Error: ${response.message}")

                }
                else {
                    _errorMessage.value = "Error: ${response.message}"
                    Log.e("UsersViewmodel", "Error: ${response.message}")
                }
            }
        }
    }

    fun getRepos(link: String) {
        viewModelScope.launch {
            _isLoading.value = true
            repository.getRepos(link).collect { response ->
                _isLoading.value = false
                if (response.data != null) {
                    Log.e("UsersViewmodel", "Error: ${response.message}")

                    _currentUser.value = _currentUser.value?.copy(repos = response.data)
                    _errorMessage.value = null
                }
                else {
                    _errorMessage.value = "Error: ${response.message}"
                    Log.e("UsersViewmodel", "Error: ${response.message}")
                }
            }
        }
    }



}
