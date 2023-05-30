package uz.gita.contactappwithauth.data.source.remote.api

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*
import uz.gita.contactappwithauth.data.source.remote.request.AddContactRequest
import uz.gita.contactappwithauth.data.source.remote.request.UpdateContactRequest
import uz.gita.contactappwithauth.data.source.remote.response.ContactResponse

interface ContactApi {

    @GET("/api/v1/contact")
    suspend fun getAllContacts(@Header("token") token:String):Response<List<ContactResponse>>

    @POST("/api/v1/contact")
    suspend fun insertContact(@Header("token") token:String,@Body request:AddContactRequest):Response<ContactResponse>

    @PUT("/api/v1/contact")
    suspend fun updateContact(@Header("token") token: String,@Body request:UpdateContactRequest):Response<ContactResponse>

    @DELETE("/api/v1/contact")
    suspend fun deleteContact(@Header("token") token: String,@Query("id") id:Int):Response<Unit>
}