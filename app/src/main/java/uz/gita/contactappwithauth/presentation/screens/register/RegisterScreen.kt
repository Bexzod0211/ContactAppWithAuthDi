package uz.gita.contactappwithauth.presentation.screens.register

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.contactappwithauth.R
import uz.gita.contactappwithauth.data.source.remote.request.RegisterRequest
import uz.gita.contactappwithauth.databinding.ScreenRegisterBinding
import uz.gita.contactappwithauth.presentation.viewmodels.RegisterViewModel

@AndroidEntryPoint
class RegisterScreen : Fragment(R.layout.screen_register) {
    private val viewModel by viewModels<RegisterViewModel>()
    private val binding by viewBinding(ScreenRegisterBinding::bind)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.openVerifyScreenLiveData.observe(this,openVerifyScreenObserver)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.apply {
            inputFirstName.doAfterTextChanged {
                viewModel.onTextChanged(it.toString(),inputLastName.text.toString(),inputPhoneNumber.text.toString(),inputPassword.text.toString(),inputConfirmPassword.text.toString())
            }
            inputLastName.doAfterTextChanged {
                viewModel.onTextChanged(inputFirstName.text.toString(),it.toString(),inputPhoneNumber.text.toString(),inputPassword.text.toString(),inputConfirmPassword.text.toString())
            }
            inputPassword.doAfterTextChanged {
                viewModel.onTextChanged(inputFirstName.text.toString(),inputLastName.toString(),inputPhoneNumber.text.toString(),it.toString(),inputConfirmPassword.text.toString())
            }
            inputPhoneNumber.doAfterTextChanged {
                viewModel.onTextChanged(inputFirstName.text.toString(),inputLastName.text.toString(),it.toString(),inputPassword.text.toString(),inputConfirmPassword.text.toString())
            }
            inputConfirmPassword.doAfterTextChanged {
                viewModel.onTextChanged(inputFirstName.text.toString(),inputLastName.text.toString(),inputPhoneNumber.text.toString(),inputPassword.text.toString(),inputConfirmPassword.text.toString())
            }
        }

        attachClickListeners()
        viewModel.toastLiveData.observe(viewLifecycleOwner,toastObserver)
        viewModel.btnRegisterEnablingStateLiveData.observe(viewLifecycleOwner,btnRegisterEnablingStateObserver)
        viewModel.progressBarLiveData.observe(viewLifecycleOwner,progressBarObserver)

    }

    private val openVerifyScreenObserver = Observer<String>{
        /*val bundle = Bundle()
        bundle.putString("Phone",it)*/
        val action = RegisterScreenDirections.actionRegisterScreenToVerifyScreen(it)
        findNavController().navigate(action)
    }

    private fun attachClickListeners(){
        binding.apply {
            btnRegister.setOnClickListener {
                viewModel.register(RegisterRequest(inputFirstName.text.toString(),inputLastName.text.toString(),inputPhoneNumber.text.toString(),inputPassword.text.toString()),inputConfirmPassword.text.toString())
            }
        }
    }

    private val toastObserver = Observer<String> {
        Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
    }

    private val btnRegisterEnablingStateObserver = Observer<Boolean> {
        binding.btnRegister.isEnabled = it
    }

    private val progressBarObserver = Observer<Boolean> {
        if (it){
            binding.progressBar.show()
        }else {
            binding.progressBar.hide()
        }
    }
}