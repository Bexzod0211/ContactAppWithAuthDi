package uz.gita.contactappwithauth.presentation.dialogs

import android.os.Bundle
import android.view.View
import androidx.core.widget.doAfterTextChanged
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import uz.gita.contactappwithauth.R
import uz.gita.contactappwithauth.data.source.remote.request.AddContactRequest
import uz.gita.contactappwithauth.databinding.AddContactDialogBinding

class AddContactDialog : BottomSheetDialogFragment(R.layout.add_contact_dialog){
    private val binding by viewBinding(AddContactDialogBinding::bind)
    private var boolFirstname = false
    private var boolLastname = false
    private var boolPhone = false
    private lateinit var onBtnSubmitClickListener:(AddContactRequest)->Unit
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.inputFirstname.doAfterTextChanged {
            boolFirstname = it.toString().trim().length in 3..20
            binding.btnSubmit.isEnabled = boolFirstname && boolLastname && boolPhone
        }

        binding.inputLastname.doAfterTextChanged {
            boolLastname = it.toString().trim().length in 3..20
            binding.btnSubmit.isEnabled = boolFirstname && boolLastname && boolPhone
        }


        binding.inputPhoneNumber.doAfterTextChanged {
            boolPhone = it.toString().trim().startsWith("+998") && it.toString().trim().length == 13
            binding.btnSubmit.isEnabled = boolFirstname && boolLastname && boolPhone
        }

        attachClickListeners()
    }

    fun setOnBtnSubmitClickListener(block:(AddContactRequest)->Unit){
        onBtnSubmitClickListener = block
    }

    private fun attachClickListeners(){
        binding.btnSubmit.setOnClickListener {
            binding.apply {
                val firstName = inputFirstname.text.toString()
                val lastName = inputLastname.text.toString()
                val phoneNumber = inputPhoneNumber.text.toString()
                onBtnSubmitClickListener.invoke(AddContactRequest(firstName,lastName,phoneNumber))
            }
            dismiss()
        }
    }

}