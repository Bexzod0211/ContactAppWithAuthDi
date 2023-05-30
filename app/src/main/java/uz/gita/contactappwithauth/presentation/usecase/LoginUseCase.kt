package uz.gita.contactappwithauth.presentation.usecase

import kotlinx.coroutines.flow.Flow
import uz.gita.contactappwithauth.data.source.remote.response.LoginResponse

interface LoginUseCase {

    fun login(phone:String,password:String):Flow<Result<LoginResponse>>
}