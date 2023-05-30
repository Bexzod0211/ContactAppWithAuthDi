package uz.gita.contactappwithauth.presentation.usecase

import kotlinx.coroutines.flow.Flow
import uz.gita.contactappwithauth.data.source.remote.request.RegisterRequest
import uz.gita.contactappwithauth.data.source.remote.response.MessageResponse

interface RegisterUseCase {
    fun register(request:RegisterRequest):Flow<Result<MessageResponse>>

}