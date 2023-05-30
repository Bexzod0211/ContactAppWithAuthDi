package uz.gita.contactappwithauth.presentation.usecase

import kotlinx.coroutines.flow.Flow

interface SplashUseCase {

    fun getLoginState():Flow<Boolean>
}