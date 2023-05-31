package uz.gita.contactappwithauth.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uz.gita.contactappwithauth.data.source.remote.request.VerifyRequest
import uz.gita.contactappwithauth.data.source.remote.response.MessageResponse
import uz.gita.contactappwithauth.data.source.remote.response.VerifyResponse
import uz.gita.contactappwithauth.navigation.AppNavigator
import uz.gita.contactappwithauth.presentation.screens.verify.VerifyScreenDirections
import uz.gita.contactappwithauth.presentation.usecase.VerifyUseCase
import javax.inject.Inject

@HiltViewModel
class VerifyViewModel @Inject constructor(
    private val useCase:VerifyUseCase,
    private val appNavigator:AppNavigator
) : ViewModel() {
    val btnSubmitEnablingLiveData = MutableLiveData<Boolean>()
    val progressBarLiveData = MutableLiveData<Boolean>()
//    val openMainScreenLiveData = MutableLiveData<Unit>()
    val errorLiveData = MutableLiveData<String>()

    fun onTextChanged(code: String) {
        btnSubmitEnablingLiveData.value = code.trim().length == 6
    }

    fun verify(phone: String, code: String) {
        progressBarLiveData.value = true


        useCase.verify(VerifyRequest(phone, code)).onEach {
            progressBarLiveData.value = false
            it.onSuccess { response ->
                useCase.saveToken(response.token)
                useCase.saveLoginState(true)
//                openMainScreenLiveData.value = Unit
                appNavigator.navigateTo(VerifyScreenDirections.actionVerifyScreenToMainScreen())
            }
            it.onFailure { e ->
                Log.d("TTT",e.message?:"")
                errorLiveData.value = e.message
            }
        }

            .launchIn(viewModelScope)

        /* repository.verifyCode(VerifyRequest(phone,code)).enqueue(object : Callback<VerifyResponse> {
            override fun onResponse(call: Call<VerifyResponse>, response: Response<VerifyResponse>) {
                progressBarLiveData.value = false
                if (response.isSuccessful){
                    response.body()?.let {
                        repository.saveToken(it.token)
                    }
                    openMainScreenLiveData.value = Unit
                }else {
                    try {
                        response.errorBody()?.let {
                            val error = Gson().fromJson(it.string(), MessageResponse::class.java)
                            errorLiveData.value = error.message
                        }
                    }catch (exception:java.lang.IllegalStateException){

                    }
                }
            }

            override fun onFailure(call: Call<VerifyResponse>, t: Throwable) {
                errorLiveData.value = "Something went wrong"
                Log.e("TTT",t.message?:"")
            }
        })
    }*/
    }
}