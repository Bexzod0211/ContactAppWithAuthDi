package uz.gita.contactappwithauth.presentation.directions.impl

import uz.gita.contactappwithauth.navigation.AppNavigator
import uz.gita.contactappwithauth.presentation.directions.MainDirection
import uz.gita.contactappwithauth.presentation.screens.main.MainScreenDirections
import javax.inject.Inject

class MainDirectionImpl @Inject constructor(
    private val appNavigator: AppNavigator
):MainDirection {
    override suspend fun openLoginScreen() {
        appNavigator.navigateTo(MainScreenDirections.actionMainScreenToLoginScreen())
    }
}