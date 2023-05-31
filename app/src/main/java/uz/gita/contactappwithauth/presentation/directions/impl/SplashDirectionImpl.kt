package uz.gita.contactappwithauth.presentation.directions.impl

import uz.gita.contactappwithauth.navigation.AppNavigator
import uz.gita.contactappwithauth.presentation.directions.SplashDirection
import uz.gita.contactappwithauth.presentation.screens.splash.SplashScreenDirections
import javax.inject.Inject

class SplashDirectionImpl @Inject constructor(
    private val appNavigator: AppNavigator
) : SplashDirection{
    override suspend fun openMainScreen() {
        appNavigator.navigateTo(SplashScreenDirections.actionSplashScreenToMainScreen())
    }

    override suspend fun openLoginScreen() {
        appNavigator.navigateTo(SplashScreenDirections.actionSplashScreenToLoginScreen())
    }
}