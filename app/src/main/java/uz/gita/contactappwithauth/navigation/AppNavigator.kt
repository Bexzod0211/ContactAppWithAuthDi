package uz.gita.contactappwithauth.navigation

interface AppNavigator {
    suspend fun navigateTo(screen:AppScreen)
    suspend fun back()
}