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
import uz.gita.contactappwithauth.data.source.remote.request.AddContactRequest
import uz.gita.contactappwithauth.data.source.remote.request.UpdateContactRequest
import uz.gita.contactappwithauth.data.source.remote.response.ContactResponse
import uz.gita.contactappwithauth.data.source.remote.response.ErrorResponse
import uz.gita.contactappwithauth.domain.AppRepository
import uz.gita.contactappwithauth.presentation.directions.MainDirection
import uz.gita.contactappwithauth.presentation.usecase.MainUseCase
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val useCase: MainUseCase,
    private val direction:MainDirection
) : ViewModel() {

    val openAddDialogLiveData = MutableLiveData<Unit>()
    val progressBarLiveData = MutableLiveData<Boolean>()
    val allContactsLiveData = MutableLiveData<List<ContactResponse>>()
    val toastLiveData = MutableLiveData<String>()
    val actionBtnLiveData = MutableLiveData<ContactResponse>()
    val editDialogLiveData = MutableLiveData<ContactResponse>()
    val deleteDialogLiveData = MutableLiveData<ContactResponse>()
    val logOutDialogLiveData = MutableLiveData<Unit>()
    val logOutLiveData = MutableLiveData<Unit>()


    init {
        loadContacts()

    }

    fun btnAddClicked() {
        openAddDialogLiveData.value = Unit
    }

    private fun loadContacts() {
        progressBarLiveData.value = true


        useCase.getAllContacts().onEach {
        progressBarLiveData.value = false
            it.onSuccess {
                allContactsLiveData.value = it
            }
            it.onFailure { e->
                Log.d("TTT",e.message?:"")
                toastLiveData.value = e.message
            }
        }
            .launchIn(viewModelScope)

        /*repository.getAllContacts().enqueue(object : Callback<List<ContactResponse>> {
            override fun onResponse(call: Call<List<ContactResponse>>, response: Response<List<ContactResponse>>) {
                progressBarLiveData.value = false
                if (response.isSuccessful) {
                    response.body()?.let {
                        allContactsLiveData.value = it
                    }
                } else {
                    try {
                        response.errorBody()?.let {
                            val error = Gson().fromJson(it.string(), ErrorResponse::class.java)
                            toastLiveData.value = error.message
                        }
                    }catch (exception:java.lang.IllegalStateException){

                    }
                }
            }

            override fun onFailure(call: Call<List<ContactResponse>>, t: Throwable) {
                progressBarLiveData.value = false
                toastLiveData.value = "Something went wrong, please try again"
                Log.e("TTT", t.message ?: "")
            }
        })*/
    }

    fun addContact(request: AddContactRequest) {
        progressBarLiveData.value = true

        useCase.addContact(request).onEach {
        progressBarLiveData.value = false
            it.onSuccess {
                loadContacts()
            }
            it.onFailure { e->
                Log.d("TTT",e.message?:"")
                toastLiveData.value = e.message
            }
        }
            .launchIn(viewModelScope)

        /*repository.addContact(request).enqueue(object : Callback<ContactResponse> {
            override fun onResponse(call: Call<ContactResponse>, response: Response<ContactResponse>) {
                progressBarLiveData.value = false
                if (response.isSuccessful) {
                    loadContacts()
                } else {
                    response.errorBody()?.let {
                        val error = Gson().fromJson(it.string(),ErrorResponse::class.java)
                        toastLiveData.value= error.message
                    }
                }
            }

            override fun onFailure(call: Call<ContactResponse>, t: Throwable) {
                progressBarLiveData.value = false
                toastLiveData.value = "Something went wrong, please try again"
                Log.e("TTT", t.message ?: "")
            }

        })*/
    }

    fun btnActionsClicked(contact:ContactResponse){
        actionBtnLiveData.value = contact
    }

    fun updateContact(request:UpdateContactRequest){
        progressBarLiveData.value = true

        useCase.updateContact(request).onEach {
            progressBarLiveData.value = false
            it.onSuccess {
                loadContacts()
            }
            it.onFailure {e->
                Log.d("TTT",e.message?:"")
                toastLiveData.value = e.message
            }
        }
            .launchIn(viewModelScope)

       /* repository.updateContact(request).enqueue(object : Callback<ContactResponse> {
            override fun onResponse(call: Call<ContactResponse>, response: Response<ContactResponse>) {
                progressBarLiveData.value = false
                if (response.isSuccessful){
                    loadContacts()
                }else {
                    response.errorBody()?.let {
                        val error = Gson().fromJson(it.string(),ErrorResponse::class.java)
                        toastLiveData.value = error.message
                    }
                }
            }

            override fun onFailure(call: Call<ContactResponse>, t: Throwable) {
                progressBarLiveData.value = false
                toastLiveData.value = "Something went wrong"
                Log.e("TTT",t.message?:"")
            }

        })*/
    }

    fun deleteContact(id:Int){
        progressBarLiveData.value = true

        useCase.deleteContact(id).onEach {
            it.onSuccess {
                loadContacts()
            }
            it.onFailure { e->
                Log.d("TTT",e.message?:"")
                toastLiveData.value = e.message
            }
        }
            .launchIn(viewModelScope)


       /* repository.deleteContact(id).enqueue(object : Callback<Unit> {
            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                progressBarLiveData.value = false
                if (response.isSuccessful){
                    loadContacts()
                }else {
                    response.errorBody()?.let {
                        val error = Gson().fromJson(it.string(),ErrorResponse::class.java)
                        toastLiveData.value = error.message
                    }
                }
            }

            override fun onFailure(call: Call<Unit>, t: Throwable) {
                progressBarLiveData.value = false
                toastLiveData.value = "Something went wrong"
                Log.e("TTT",t.message?:"")
            }

        })*/

    }

    fun btnEditClicked(contact:ContactResponse){
        editDialogLiveData.value = contact
    }

    fun btnDeleteClicked(contact: ContactResponse){
        deleteDialogLiveData.value = contact
    }

    fun btnLogOutClicked(){
        logOutDialogLiveData.value = Unit
    }

    fun logOut(){
        useCase.saveLoginState(false)
        viewModelScope.launch {
            direction.openLoginScreen()
        }
    }
}