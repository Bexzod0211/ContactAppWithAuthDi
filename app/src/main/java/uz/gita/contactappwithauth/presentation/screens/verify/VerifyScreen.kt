package uz.gita.contactappwithauth.presentation.screens.verify

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.contactappwithauth.R
import uz.gita.contactappwithauth.databinding.ScreenVerifyBinding
import uz.gita.contactappwithauth.presentation.viewmodels.VerifyViewModel


@AndroidEntryPoint
class VerifyScreen : Fragment(R.layout.screen_verify) {
    private val binding by viewBinding(ScreenVerifyBinding::bind)
    private val viewModel by viewModels<VerifyViewModel>()
    private val args : VerifyScreenArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.openMainScreenLiveData.observe(this,openMainScreenObserver)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            inputSmsCode.doAfterTextChanged {
                viewModel.onTextChanged(it.toString())
            }
        }

        attachClickListeners()

        viewModel.errorLiveData.observe(viewLifecycleOwner,errorObserver)
        viewModel.btnSubmitEnablingLiveData.observe(viewLifecycleOwner,btnSubmitEnablingObserver)
        viewModel.progressBarLiveData.observe(viewLifecycleOwner,progressBarObserver)
    }

    private fun attachClickListeners(){
        binding.apply {
            btnVerify.setOnClickListener {
                viewModel.verify(args.phone,inputSmsCode.text.toString())
            }
        }
    }

    private val errorObserver = Observer<String>{
        Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
    }

    private val openMainScreenObserver = Observer<Unit> {
        findNavController().navigate(R.id.action_verifyScreen_to_mainScreen)
    }

    private val btnSubmitEnablingObserver = Observer<Boolean> {
        binding.btnVerify.isEnabled = it
    }

    private val progressBarObserver = Observer<Boolean> {
        if (it){
            binding.progressBar.show()
        }else {
            binding.progressBar.hide()
        }
    }
}