package uz.gita.contactappwithauth.domain

import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Call
import retrofit2.Response
import uz.gita.contactappwithauth.data.source.local.MySharedPreferences
import uz.gita.contactappwithauth.data.source.remote.api.AuthApi
import uz.gita.contactappwithauth.data.source.remote.api.ContactApi
import uz.gita.contactappwithauth.data.source.remote.request.*
import uz.gita.contactappwithauth.data.source.remote.response.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppRepository @Inject constructor(
    private val contactApi: ContactApi,
    private val pref: MySharedPreferences
) {

    @Inject
     lateinit var authApi: AuthApi


//    private val contactApi = MyClient.retrofit.create()


    /* companion object {
         private lateinit var instance:AppRepository

         fun init(){
             if (!::instance.isInitialized){
                 instance = AppRepository()
             }
         }

         fun getInstance():AppRepository = instance
     }*/

    fun register(request: RegisterRequest): Flow<Result<MessageResponse>> = flow<Result<MessageResponse>> {
        val response = authApi.register(request)

        if (response.isSuccessful) {
            response.body()?.let {
                emit(Result.success(it))
            }
        } else {
            response.errorBody()?.let {
                val error = Gson().fromJson(it.string(),ErrorResponse::class.java)
                emit(Result.failure(Exception(error.message)))
            }
        }
    }
        .catch {
            emit(Result.failure(it))
        }
        .flowOn(Dispatchers.IO)

    fun login(request: LoginRequest): Flow<Result<LoginResponse>> = flow<Result<LoginResponse>>{
        val response = authApi.login(request)
        if (response.isSuccessful){
            response.body()?.let {
                saveToken(it.token)
                saveLoginState(true)
                emit(Result.success(it))
            }
        }
        else {
            response.errorBody()?.let {
                val error = Gson().fromJson(it.string(),ErrorResponse::class.java)
                emit(Result.failure(Exception(error.message)))
            }

        }
    }
        .catch {
            emit(Result.failure(it))
        }
        .flowOn(Dispatchers.IO)


    fun verifyCode(request: VerifyRequest): Flow<Result<VerifyResponse>> = flow<Result<VerifyResponse>> {
        val response = authApi.verifyCode(request)

        if (response.isSuccessful){
            response.body()?.let {
                emit(Result.success(it))
            }
        }else {
            response.errorBody()?.let {
                val error = Gson().fromJson(it.string(),ErrorResponse::class.java)
                emit(Result.failure(Exception(error.message)))
            }
        }
    }
        .catch {
            emit(Result.failure(it))
        }
        .flowOn(Dispatchers.IO)

    fun saveLoginState(state: Boolean) {
        pref.loginState = state
    }

    fun getLoginState(): Flow<Boolean>  = flow{
        emit(pref.loginState)
    }

    fun getToken(): String {
        return pref.token
    }

    fun saveToken(token: String) {
        pref.token = token
    }

    fun addContact(request: AddContactRequest): Flow<Result<ContactResponse>> = flow<Result<ContactResponse>> {
        val response =  contactApi.insertContact(pref.token, request)
        if (response.isSuccessful){
            response.body()?.let {
                emit(Result.success(it))
            }
        }else {
            response.errorBody()?.let {
                val error = Gson().fromJson(it.string(),ErrorResponse::class.java)
                emit(Result.failure(Exception(error.message)))
            }
        }
    }.catch {
        emit(Result.failure(it))
    }.flowOn(Dispatchers.IO)

    fun getAllContacts(): Flow<Result<List<ContactResponse>>> = flow<Result<List<ContactResponse>>> {
        val response =  contactApi.getAllContacts(pref.token)

        if (response.isSuccessful){
            response.body()?.let {
                emit(Result.success(it))
            }
        }
        else {
            response.errorBody()?.let {
                val error = Gson().fromJson(it.string(),ErrorResponse::class.java)
                emit(Result.failure(Exception(error.message)))
            }
        }
    }
        .catch {
            emit(Result.failure(it))
        }
        .flowOn(Dispatchers.IO)

    fun updateContact(request: UpdateContactRequest): Flow<Result<ContactResponse>> = flow<Result<ContactResponse>> {
        val response =  contactApi.updateContact(pref.token, request)

        if (response.isSuccessful){
            response.body()?.let {
                emit(Result.success(it))
            }
        }else {
            response.errorBody()?.let {
                val error = Gson().fromJson(it.string(),ErrorResponse::class.java)
                emit(Result.failure(Exception(error.message)))
            }
        }
    }
        .catch {
        emit(Result.failure(it))
    }
        .flowOn(Dispatchers.IO)

    fun deleteContact(id: Int): Flow<Result<Unit>> = flow<Result<Unit>> {
        val response =  contactApi.deleteContact(pref.token, id)
        if (response.isSuccessful){
            response.body()?.let {
                emit(Result.success(it))
            }
        }else {
            response.errorBody()?.let {
                val error = Gson().fromJson(it.string(),ErrorResponse::class.java)
                emit(Result.failure(Exception(error.message)))
            }
        }
    }
        .catch {
            emit(Result.failure(it))
        }
        .flowOn(Dispatchers.IO)
}