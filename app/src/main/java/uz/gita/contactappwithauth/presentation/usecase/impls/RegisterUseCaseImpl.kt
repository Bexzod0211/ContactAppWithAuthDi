package uz.gita.contactappwithauth.presentation.usecase.impls

import kotlinx.coroutines.flow.Flow
import uz.gita.contactappwithauth.data.source.remote.request.RegisterRequest
import uz.gita.contactappwithauth.data.source.remote.response.MessageResponse
import uz.gita.contactappwithauth.domain.AppRepository
import uz.gita.contactappwithauth.presentation.usecase.RegisterUseCase
import javax.inject.Inject

class RegisterUseCaseImpl @Inject constructor(
    private val repository: AppRepository
) : RegisterUseCase {
    override fun register(request: RegisterRequest): Flow<Result<MessageResponse>> {
        return repository.register(request)
    }

}