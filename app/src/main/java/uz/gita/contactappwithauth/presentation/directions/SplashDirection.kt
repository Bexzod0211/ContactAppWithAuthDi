package uz.gita.contactappwithauth.presentation.directions

interface SplashDirection {
    suspend fun openMainScreen()
    suspend fun openLoginScreen()
}