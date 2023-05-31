package uz.gita.contactappwithauth.presentation.directions

interface RegisterDirection {
    suspend fun openVerifyScreen(phone:String)
}