package uz.gita.contactappwithauth.presentation.usecase.impls

import kotlinx.coroutines.flow.Flow
import uz.gita.contactappwithauth.data.source.remote.request.LoginRequest
import uz.gita.contactappwithauth.data.source.remote.response.LoginResponse
import uz.gita.contactappwithauth.domain.AppRepository
import uz.gita.contactappwithauth.presentation.usecase.LoginUseCase
import javax.inject.Inject

class LoginUseCaseImpl @Inject constructor(
    private val repository: AppRepository
) : LoginUseCase {
    override fun login(phone: String, password: String): Flow<Result<LoginResponse>> {
        return repository.login(LoginRequest(phone, password))
    }


}