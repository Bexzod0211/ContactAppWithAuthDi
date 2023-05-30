package uz.gita.contactappwithauth.presentation.usecase.impls

import kotlinx.coroutines.flow.Flow
import uz.gita.contactappwithauth.domain.AppRepository
import uz.gita.contactappwithauth.presentation.usecase.SplashUseCase
import javax.inject.Inject

class SplashUseCaseImpl @Inject constructor(
    private val repository: AppRepository
) : SplashUseCase {
    override fun getLoginState(): Flow<Boolean> {
        return repository.getLoginState()
    }
}