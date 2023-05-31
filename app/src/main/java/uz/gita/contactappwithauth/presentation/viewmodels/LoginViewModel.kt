package uz.gita.contactappwithauth.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uz.gita.contactappwithauth.data.source.remote.request.LoginRequest
import uz.gita.contactappwithauth.data.source.remote.response.ErrorResponse
import uz.gita.contactappwithauth.data.source.remote.response.LoginResponse
import uz.gita.contactappwithauth.domain.AppRepository
import uz.gita.contactappwithauth.navigation.AppNavigator
import uz.gita.contactappwithauth.presentation.directions.LoginDirection
import uz.gita.contactappwithauth.presentation.screens.login.LoginScreenDirections
import uz.gita.contactappwithauth.presentation.screens.register.RegisterScreenDirections
import uz.gita.contactappwithauth.presentation.usecase.LoginUseCase
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val useCase:LoginUseCase,
    private val direction:LoginDirection
) : ViewModel() {

//    val openRegisterScreenLiveData = MutableLiveData<Unit>()
//    val openMainScreenLiveData = MutableLiveData<Unit>()
    val btnLoginEnablingStateLiveData = MutableLiveData<Boolean>()
    val errorHandleLiveData = MutableLiveData<String>()
    val progressBarLiveData = MutableLiveData<Boolean>()

    fun btnRegisterClicked() {
//        openRegisterScreenLiveData.value = Unit
        viewModelScope.launch {
            direction.openRegisterScreen()
        }
    }


    fun onTextChanged(phone: String, password: String) {
        btnLoginEnablingStateLiveData.value = phone.trim().startsWith("+998")
                && phone.trim().length == 13 && password.trim().length >= 6
    }

    fun login(phone: String, password: String) {
        progressBarLiveData.value = true

        useCase.login(phone, password).onEach {
            it.onSuccess {
//                openMainScreenLiveData.value = Unit
                direction.openMainScreen()
            }
            it.onFailure { e->
                Log.d("TTT",e.message?:"")
                errorHandleLiveData.value = e.message
            }
            progressBarLiveData.value = false

        }
            .launchIn(viewModelScope)



        /*repository.login(LoginRequest(phone, password)).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                progressBarLiveData.value = false
                if (response.isSuccessful) {
                    response.body()?.let {
                        repository.saveToken(it.token)
                    }
                    openMainScreenLiveData.value = Unit
                    repository.saveLoginState(true)
                } else {
                    response.errorBody()?.let {
                        try {
                            val error = Gson().fromJson(it.string(), ErrorResponse::class.java)
                            errorHandleLiveData.value = error.message
                        }
                        catch (exception:java.lang.IllegalStateException){
                            errorHandleLiveData.value= exception.message
                        }
                    }
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                errorHandleLiveData.value = "Something went wrong, please try again"
                Log.e("TTT", t.message ?: "")
                progressBarLiveData.value = false
            }

        })*/
    }


}