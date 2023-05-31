package uz.gita.contactappwithauth.presentation.directions.impl

import uz.gita.contactappwithauth.navigation.AppNavigator
import uz.gita.contactappwithauth.presentation.directions.LoginDirection
import uz.gita.contactappwithauth.presentation.screens.login.LoginScreenDirections
import javax.inject.Inject

class LoginDirectionImpl @Inject constructor(
    private val appNavigator: AppNavigator
) : LoginDirection{
    override suspend fun openRegisterScreen() {
        appNavigator.navigateTo(LoginScreenDirections.actionLoginScreenToRegisterScreen())
    }

    override suspend fun openMainScreen() {
        appNavigator.navigateTo(LoginScreenDirections.actionLoginScreenToMainScreen())
    }
}