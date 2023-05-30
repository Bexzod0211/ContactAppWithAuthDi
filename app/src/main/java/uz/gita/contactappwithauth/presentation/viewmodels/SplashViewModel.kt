package uz.gita.contactappwithauth.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.contactappwithauth.domain.AppRepository
import uz.gita.contactappwithauth.presentation.usecase.SplashUseCase
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val useCase:SplashUseCase
) : ViewModel(){

    val openMainScreenLiveData = MutableLiveData<Unit>()
    val openLoginScreenLiveData = MutableLiveData<Unit>()

    init {
        init()
    }

    fun init(){
        useCase.getLoginState().onEach {
            Log.d("TTT","it = $$it")
            if (it) {
                openMainScreenLiveData.value = Unit
            } else {
                openLoginScreenLiveData.value = Unit
            }
        }
            .launchIn(viewModelScope)
    }
}