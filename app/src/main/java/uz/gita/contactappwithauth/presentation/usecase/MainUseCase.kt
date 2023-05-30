package uz.gita.contactappwithauth.presentation.usecase

import kotlinx.coroutines.flow.Flow
import uz.gita.contactappwithauth.data.source.remote.request.AddContactRequest
import uz.gita.contactappwithauth.data.source.remote.request.UpdateContactRequest
import uz.gita.contactappwithauth.data.source.remote.response.ContactResponse

interface MainUseCase {
    fun getAllContacts():Flow<Result<List<ContactResponse>>>
    fun addContact(request:AddContactRequest):Flow<Result<ContactResponse>>
    fun updateContact(request:UpdateContactRequest):Flow<Result<ContactResponse>>
    fun deleteContact(id:Int):Flow<Result<Unit>>
    fun saveLoginState(state:Boolean)
}