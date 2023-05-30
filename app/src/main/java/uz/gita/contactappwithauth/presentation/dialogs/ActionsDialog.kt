package uz.gita.contactappwithauth.presentation.dialogs

import android.os.Bundle
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import uz.gita.contactappwithauth.R
import uz.gita.contactappwithauth.data.source.remote.response.ContactResponse
import uz.gita.contactappwithauth.databinding.DialogActionsBinding

class ActionsDialog(private val contact:ContactResponse) : BottomSheetDialogFragment(R.layout.dialog_actions){
    private val binding by viewBinding(DialogActionsBinding::bind)
    private lateinit var onEditButtonClickListener:()->Unit
    private lateinit var onDeleteButtonClickListener:()->Unit
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        attachClickListeners()
    }

    private fun attachClickListeners(){
        binding.btnEdit.setOnClickListener {
            onEditButtonClickListener.invoke()
            dismiss()
        }
        binding.btnDelete.setOnClickListener {
            onDeleteButtonClickListener.invoke()
            dismiss()
        }
    }

    fun setOnEditButtonClickListener(block:()->Unit){
        onEditButtonClickListener = block
    }

    fun setOnDeleteButtonClickListener(block:()->Unit){
        onDeleteButtonClickListener = block
    }

}