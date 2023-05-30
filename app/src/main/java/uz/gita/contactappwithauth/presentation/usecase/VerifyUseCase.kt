package uz.gita.contactappwithauth.presentation.usecase

import kotlinx.coroutines.flow.Flow
import uz.gita.contactappwithauth.data.source.remote.request.VerifyRequest
import uz.gita.contactappwithauth.data.source.remote.response.VerifyResponse

interface VerifyUseCase {

    fun verify(request:VerifyRequest):Flow<Result<VerifyResponse>>
    fun saveToken(token:String)
    fun saveLoginState(state:Boolean)
}