package uz.gita.contactappwithauth.presentation.directions.impl

import uz.gita.contactappwithauth.navigation.AppNavigator
import uz.gita.contactappwithauth.presentation.directions.VerifyDirection
import uz.gita.contactappwithauth.presentation.screens.verify.VerifyScreenDirections
import javax.inject.Inject

class VerifyDirectionImpl @Inject constructor(
    private val appNavigator: AppNavigator
) : VerifyDirection {

    override suspend fun openMainScreen() {
        appNavigator.navigateTo(VerifyScreenDirections.actionVerifyScreenToMainScreen())
    }

}
