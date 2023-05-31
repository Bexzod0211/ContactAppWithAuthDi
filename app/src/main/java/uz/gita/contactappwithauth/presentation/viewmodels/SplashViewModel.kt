package uz.gita.contactappwithauth.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import uz.gita.contactappwithauth.domain.AppRepository
import uz.gita.contactappwithauth.navigation.AppNavigator
import uz.gita.contactappwithauth.presentation.screens.splash.SplashScreenDirections
import uz.gita.contactappwithauth.presentation.usecase.SplashUseCase
import uz.gita.contactappwithauth.utils.myLog
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val useCase:SplashUseCase,
    private val appNavigator:AppNavigator
) : ViewModel(){
//
//    val openMainScreenLiveData = MutableLiveData<Unit>()
//    val openLoginScreenLiveData = MutableLiveData<Unit>()

//    init {
//        init()
//    }

    fun init(){
        viewModelScope.launch {
            delay(2000)
            useCase.getLoginState().collect {
                Log.d("TTT", "it = $$it")
                if (it) {
//                openMainScreenLiveData.value = Unit
                    appNavigator.navigateTo(SplashScreenDirections.actionSplashScreenToMainScreen())
                } else {
//                openLoginScreenLiveData.value = Unit
                    myLog("else")
                    appNavigator.navigateTo(SplashScreenDirections.actionSplashScreenToLoginScreen())
                }
            }

        }
    }
}