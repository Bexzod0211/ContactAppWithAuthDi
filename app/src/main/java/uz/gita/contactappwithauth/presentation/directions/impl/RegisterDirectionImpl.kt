package uz.gita.contactappwithauth.presentation.directions.impl

import uz.gita.contactappwithauth.navigation.AppNavigator
import uz.gita.contactappwithauth.presentation.directions.RegisterDirection
import uz.gita.contactappwithauth.presentation.screens.register.RegisterScreenDirections
import javax.inject.Inject

class RegisterDirectionImpl @Inject constructor(
    private val appNavigator: AppNavigator
):RegisterDirection{
    override suspend fun openVerifyScreen(phone: String) {
        appNavigator.navigateTo(RegisterScreenDirections.actionRegisterScreenToVerifyScreen(phone))
    }
}