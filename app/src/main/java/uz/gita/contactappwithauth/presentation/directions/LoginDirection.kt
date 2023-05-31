package uz.gita.contactappwithauth.presentation.directions

interface LoginDirection {
    suspend fun openRegisterScreen()
    suspend fun openMainScreen()
}