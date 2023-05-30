package uz.gita.contactappwithauth.data.source.remote.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import uz.gita.contactappwithauth.data.source.remote.request.LoginRequest
import uz.gita.contactappwithauth.data.source.remote.request.RegisterRequest
import uz.gita.contactappwithauth.data.source.remote.request.VerifyRequest
import uz.gita.contactappwithauth.data.source.remote.response.LoginResponse
import uz.gita.contactappwithauth.data.source.remote.response.MessageResponse
import uz.gita.contactappwithauth.data.source.remote.response.VerifyResponse

interface AuthApi {

    @POST("/api/v1/register")
    suspend fun register(@Body request:RegisterRequest):Response<MessageResponse>

    @POST("/api/v1/register/verify")
    suspend fun verifyCode(@Body request:VerifyRequest):Response<VerifyResponse>

    @POST("/api/v1/login")
    suspend fun login(@Body request:LoginRequest):Response<LoginResponse>


}