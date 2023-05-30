package uz.gita.contactappwithauth.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import uz.gita.contactappwithauth.presentation.usecase.*
import uz.gita.contactappwithauth.presentation.usecase.impls.*

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {
    @Binds
    fun bindLoginUseCase(impl: LoginUseCaseImpl): LoginUseCase

    @Binds
    fun bindRegisterUseCase(impl: RegisterUseCaseImpl): RegisterUseCase

    @Binds
    fun bindVerifyUseCase(impl:VerifyUseCaseImpl):VerifyUseCase

    @Binds
    fun bindSplashUseCase(impl:SplashUseCaseImpl):SplashUseCase

    @Binds
    fun bindMainUseCase(impl:MainUseCaseImpl):MainUseCase
}