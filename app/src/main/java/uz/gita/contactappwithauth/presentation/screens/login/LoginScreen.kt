package uz.gita.contactappwithauth.presentation.screens.login

import android.animation.ValueAnimator
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.animation.doOnEnd
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import uz.gita.contactappwithauth.R
import uz.gita.contactappwithauth.databinding.ScreenLoginBinding
import uz.gita.contactappwithauth.presentation.viewmodels.LoginViewModel

@AndroidEntryPoint
class LoginScreen : Fragment(R.layout.screen_login) {
    private lateinit var viewModel: LoginViewModel
    private val binding by viewBinding(ScreenLoginBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        viewModel.openRegisterScreenLiveData.observe(this, openRegisterScreenObserver)
        viewModel.openMainScreenLiveData.observe(this, openMainScreenObserver)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.apply {
            inputPhoneNumber.doAfterTextChanged {
                viewModel.onTextChanged(it.toString(), inputPassword.text.toString())
            }
            inputPassword.doAfterTextChanged {
                viewModel.onTextChanged(inputPhoneNumber.text.toString(), it.toString())
            }

        }
        attachClickListeners()

        viewModel.btnLoginEnablingStateLiveData.observe(viewLifecycleOwner, btnLoginEnablingStateObserver)
        viewModel.errorHandleLiveData.observe(viewLifecycleOwner, errorHandleObserver)
        viewModel.progressBarLiveData.observe(viewLifecycleOwner, progressBarObserver)
    }


    private fun attachClickListeners() {
        binding.apply {
            btnRegister.setOnClickListener {
                lifecycleScope.launch {
                    animateByX(it)
                    delay(2000)
                    viewModel.btnRegisterClicked()
                }
            }

            btnLogin.setOnClickListener {
                lifecycleScope.launch {
                    animateByY(it)
                    delay(1000)
                    viewModel.login(inputPhoneNumber.text.toString(), inputPassword.text.toString())
                }
            }
        }
    }


    private val openRegisterScreenObserver = Observer<Unit> {
        findNavController().navigate(R.id.action_loginScreen_to_registerScreen)
    }

    private val btnLoginEnablingStateObserver = Observer<Boolean> {
        binding.btnLogin.isEnabled = it
    }

    private val errorHandleObserver = Observer<String> {
        Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
    }

    private val progressBarObserver = Observer<Boolean> {
        if (it) {
            binding.progressBar.show()
        } else {
            binding.progressBar.hide()
        }
    }

    private val openMainScreenObserver = Observer<Unit> {
        findNavController().navigate(R.id.action_loginScreen_to_mainScreen)
    }

    private fun animateByX(view: View) {
        /*view.animate().
            setDuration(1000)
            .x(200f)
            .withEndAction {
                view.animate().setDuration(1000)
                    .x(-200f)
                    .start()
            }
            .start()*/
        ValueAnimator.ofInt(0, 150).apply {
            addUpdateListener {
                val value = (it.animatedValue as Int).toFloat()
                view.x = value
            }
            duration = 1000
            start()
        }
            .doOnEnd {
                ValueAnimator.ofInt(150, 0).apply {
                    addUpdateListener {
                        val value = it.animatedValue as Int
                        view.x = value.toFloat()
                    }
                    duration = 500
                    start()
                }
                    .doOnEnd {
                        ValueAnimator.ofInt(0, 100).apply {
                            addUpdateListener {
                                val value = it.animatedValue as Int
                                view.x = value.toFloat()
                            }
                            duration = 500
                            start()
                        }
                    }
            }
    }

    private fun animateByY(view:View){
        val y = view.y
        ValueAnimator.ofFloat(y,y-100).apply {
            addUpdateListener {
                val value = it.animatedValue as Float
                view.y = value
            }
            duration = 500
            start()
        }.doOnEnd {
            ValueAnimator.ofFloat(y-100,y).apply {
                addUpdateListener {
                    val value = it.animatedValue as Float
                    view.y = value
                }

                duration = 500
                start()
            }
        }

    }
}