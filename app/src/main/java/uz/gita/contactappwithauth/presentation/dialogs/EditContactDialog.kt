package uz.gita.contactappwithauth.presentation.dialogs

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.WindowManager
import androidx.core.widget.doAfterTextChanged
import uz.gita.contactappwithauth.data.source.remote.request.UpdateContactRequest
import uz.gita.contactappwithauth.data.source.remote.response.ContactResponse
import uz.gita.contactappwithauth.databinding.AddContactDialogBinding

class EditContactDialog(private val contact: ContactResponse, context: Context) : AlertDialog(context) {
    private lateinit var binding: AddContactDialogBinding
    private var boolFirstname = true
    private var boolLastname = true
    private var boolPhone = true
    private lateinit var onBtnSubmitClickListener: (UpdateContactRequest) -> Unit
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window?.clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
        window?.clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        binding = AddContactDialogBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            inputFirstname.setText(contact.firstName)
            inputLastname.setText(contact.lastName)
            inputPhoneNumber.setText(contact.phone)
            btnSubmit.isEnabled = true
            btnSubmit.text = "Edit contact"
        }

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


    fun setOnBtnSubmitClickListener(block: (UpdateContactRequest) -> Unit) {
        onBtnSubmitClickListener = block
    }

    private fun attachClickListeners() {
        binding.apply {
            btnSubmit.setOnClickListener {
                val firstName = inputFirstname.text.toString()
                val lastName = inputLastname.text.toString()
                val phoneNumber = inputPhoneNumber.text.toString()
                onBtnSubmitClickListener.invoke(UpdateContactRequest(contact.id, firstName, lastName, phoneNumber))
            }
            dismiss()
        }
    }
}
