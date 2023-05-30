package uz.gita.contactappwithauth.presentation.usecase.impls

import kotlinx.coroutines.flow.Flow
import uz.gita.contactappwithauth.data.source.remote.request.AddContactRequest
import uz.gita.contactappwithauth.data.source.remote.request.UpdateContactRequest
import uz.gita.contactappwithauth.data.source.remote.response.ContactResponse
import uz.gita.contactappwithauth.domain.AppRepository
import uz.gita.contactappwithauth.presentation.usecase.MainUseCase
import javax.inject.Inject

class MainUseCaseImpl @Inject constructor(
    private val repository:AppRepository
) : MainUseCase {
    override fun getAllContacts(): Flow<Result<List<ContactResponse>>> {
        return repository.getAllContacts()
    }

    override fun addContact(request: AddContactRequest): Flow<Result<ContactResponse>> {
        return repository.addContact(request)
    }

    override fun updateContact(request: UpdateContactRequest): Flow<Result<ContactResponse>> {
        return repository.updateContact(request)
    }

    override fun deleteContact(id: Int): Flow<Result<Unit>> {
        return repository.deleteContact(id)
    }

    override fun saveLoginState(state: Boolean) {
        repository.saveLoginState(state)
    }

}