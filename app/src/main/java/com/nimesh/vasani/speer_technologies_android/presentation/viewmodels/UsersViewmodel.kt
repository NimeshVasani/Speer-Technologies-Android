package com.nimesh.vasani.speer_technologies_android.presentation.viewmodels


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nimesh.vasani.speer_technologies_android.data.model.GitHubUserResponse
import com.nimesh.vasani.speer_technologies_android.data.model.User
import com.nimesh.vasani.speer_technologies_android.data.model.UserState
import com.nimesh.vasani.speer_technologies_android.data.repositories.UsersRepository
import com.nimesh.vasani.speer_technologies_android.others.Response
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class UsersViewmodel(
    private val repository: UsersRepository
) : ViewModel() {


    private val _uiState = MutableStateFlow<UserState>(UserState())
    val uiState: StateFlow<UserState> = _uiState.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    fun searchUsers(query: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            val response = repository.searchGitHubUsers(query)
            when(response){
                is Response.Success -> {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        users = response.data?.items ?: emptyList()
                    )}
                is Response.Error -> {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        error = response.message ?: "An unknown error occurred"
                    )
                }
                is Response.Loading -> {
                    _uiState.value = _uiState.value.copy(isLoading = true)
                }
            }

        }
    }


    fun getFollowers(link: String, currentUser:User) {
        _uiState.value = _uiState.value.copy(isLoading = true)

        viewModelScope.launch {

            if(!_uiState.value.users.contains(currentUser)
            ){
                _uiState.value = _uiState.value.copy(users = _uiState.value.users + currentUser)
            }
            val followersResponse = repository.getFollowersFollowing(link)
            when (followersResponse) {
                is Response.Success -> {
                    val updatedUser= _uiState.value.users.map { user ->
                        if (user.login == currentUser.login) {
                            user.copy(followers = followersResponse.data)
                        } else {
                            user
                        }
                    }
                    // Update the UI state with the chapters that now include verses
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        users = updatedUser
                    )
                }
                is Response.Error -> {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        error = followersResponse.message ?: "An unknown error occurred"
                    )
                }
                is Response.Loading -> {
                    _uiState.value = _uiState.value.copy(isLoading = true)
                }
            }
        }
    }

    fun getRepos(link: String,login:String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)

            viewModelScope.launch {
                delay(2000L)
                val reposResponse = repository.getRepos(link)
                when (reposResponse) {
                    is Response.Success -> {
                        val updatedUser= _uiState.value.users.map { user ->
                            if (user.login == login) {

                                user.copy(repos = reposResponse.data)
                            } else {
                                user
                            }
                        }
                        // Update the UI state with the chapters that now include verses
                        _uiState.value = _uiState.value.copy(
                            isLoading = false,
                            users = updatedUser
                        )
                    }
                    is Response.Error -> {
                        _uiState.value = _uiState.value.copy(
                            isLoading = false,
                            error = reposResponse.message ?: "An unknown error occurred"
                        )
                    }
                    is Response.Loading -> {
                        _uiState.value = _uiState.value.copy(isLoading = true)
                    }
                }
            }
        }
    }
}


