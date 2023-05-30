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
import uz.gita.contactappwithauth.data.source.remote.request.RegisterRequest
import uz.gita.contactappwithauth.data.source.remote.response.MessageResponse
import uz.gita.contactappwithauth.domain.AppRepository
import uz.gita.contactappwithauth.presentation.usecase.RegisterUseCase
import javax.inject.Inject


@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val useCase: RegisterUseCase
) : ViewModel() {
    val btnRegisterEnablingStateLiveData = MutableLiveData<Boolean>()
    val toastLiveData = MutableLiveData<String>()
    val progressBarLiveData = MutableLiveData<Boolean>()
    val openVerifyScreenLiveData = MutableLiveData<String>()

    fun onTextChanged(firstName:String,lastName:String,phoneNumber:String,password:String,confirmPassword:String){
        btnRegisterEnablingStateLiveData.value = firstName.trim().length in 3..20
                &&lastName.trim().length in 3..20&&phoneNumber.trim().startsWith("+998") && phoneNumber.trim().length == 13
                &&password.trim().length in 6 until 20 && confirmPassword.trim().length in 6 until 20
    }

    fun register(request:RegisterRequest,confirmPassword: String){
        if (request.password != confirmPassword){
            toastLiveData.value = "Passwords do not match"
            return
        }

        progressBarLiveData.value = true

        useCase.register(request).onEach {
            progressBarLiveData.value = false
            it.onSuccess { m->
                toastLiveData.value = m.message
                openVerifyScreenLiveData.value = request.phone
            }
            it.onFailure {e->
                Log.d("TTT",e.message?:"")
                toastLiveData.value = e.message
            }
        }

            .launchIn(viewModelScope)
        /*repository.register(request).enqueue(object : Callback<MessageResponse> {
            override fun onResponse(call: Call<MessageResponse>, response: Response<MessageResponse>) {
                progressBarLiveData.value = false
                if (response.isSuccessful){
                    response.body()?.let {
                        toastLiveData.value = it.message
                    }
                    openVerifyScreenLiveData.value = request.phone
                }else {
                    try {
                        response.errorBody()?.let {
                            val error = Gson().fromJson(it.string(), MessageResponse::class.java)
                            toastLiveData.value = error.message
                        }
                    }catch (exception:java.lang.IllegalStateException){

                    }
                }
            }

            override fun onFailure(call: Call<MessageResponse>, t: Throwable) {
                progressBarLiveData.value = false
                toastLiveData.value = "Something went wrong"
                Log.e("TTT",t.message?:"")
            }
        })*/
    }
}