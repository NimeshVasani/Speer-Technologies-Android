# Speer Technologies Android Assessment

This project is an Android app developed as part of the Speer Technologies Android Developer assessment. It follows modern Android development principles and best practices, including:

- **MVVM Architecture**
- **Jetpack Compose for UI**
- **StateFlow for state management**
- **Koin for Dependency Injection**
- **Ktor for network calls**

## Features

- **User List**: Displays a list of users fetched from the GitHub API.
- **User Profile**: Shows detailed information about a selected user, including followers, following, and repositories.
- **Pull-to-refresh**: Allows users to refresh data on the profile screen.

## Tech Stack

- **Jetpack Compose**: A modern toolkit for building native UIs in Android.
- **Kotlin**: The primary language for Android development.
- **Ktor**: A Kotlin-based HTTP client used for network calls.
- **Koin**: A lightweight dependency injection library for Kotlin.
- **StateFlow**: A state management tool for handling UI state.

## Architecture

### MVVM (Model-View-ViewModel)

The app follows the **MVVM** architecture pattern to separate concerns and promote maintainability. The ViewModel handles all the logic and data fetching, while the UI is updated through StateFlow.

- **Model**: Represents the data and network layers (GitHub API in this case).
- **ViewModel**: Handles business logic and state management using StateFlow.
- **View**: Built with Jetpack Compose to display the UI.

### Koin for Dependency Injection

Koin is used to inject dependencies like ViewModels into Composables, enabling a clean and testable architecture.

### Ktor for Network Calls

Ktor is used for making network requests to the GitHub API, fetching user data, followers, repositories, and following.

## Setup and Installation

1. Clone the repository:

    ```bash
    git clone https://github.com/your-username/speer-technologies-android-assessment.git
    cd speer-technologies-android-assessment
    ```

2. Open the project in Android Studio.

3. Ensure you have the necessary SDKs and dependencies installed.

4. Build and run the app on an emulator or physical device.

## Code Overview

### Dependency Injection with Koin

```kotlin
val appModule = module {
    viewModel { UsersViewModel(get()) }
    single { GitHubRepository(get()) }
    single { KtorClient() }
}

startKoin {
    androidContext(this@App)
    modules(appModule)
}
