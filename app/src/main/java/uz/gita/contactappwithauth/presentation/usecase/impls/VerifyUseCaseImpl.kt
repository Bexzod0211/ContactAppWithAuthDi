package uz.gita.contactappwithauth.presentation.usecase.impls

import kotlinx.coroutines.flow.Flow
import uz.gita.contactappwithauth.data.source.remote.request.VerifyRequest
import uz.gita.contactappwithauth.data.source.remote.response.VerifyResponse
import uz.gita.contactappwithauth.domain.AppRepository
import uz.gita.contactappwithauth.presentation.usecase.VerifyUseCase
import javax.inject.Inject

class VerifyUseCaseImpl @Inject constructor(
    private val repository: AppRepository
) : VerifyUseCase {
    override fun verify(request: VerifyRequest): Flow<Result<VerifyResponse>> {
        return repository.verifyCode(request)
    }

    override fun saveToken(token: String) {
        repository.saveToken(token)
    }

    override fun saveLoginState(state: Boolean) {
        repository.saveLoginState(state)
    }

}