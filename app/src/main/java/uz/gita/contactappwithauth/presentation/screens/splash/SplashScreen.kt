package uz.gita.contactappwithauth.presentation.screens.splash

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.contactappwithauth.R
import uz.gita.contactappwithauth.presentation.viewmodels.SplashViewModel

@AndroidEntryPoint
@SuppressLint("CustomSplashScreen")
class SplashScreen : Fragment(R.layout.screen_splash) {
    private val viewModel by viewModels<SplashViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.openMainScreenLiveData.observe(this,openMainScreenObserver)
        viewModel.openLoginScreenLiveData.observe(this,openLoginScreenObserver)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private val openMainScreenObserver = Observer<Unit>{
        val handler = Handler(Looper.getMainLooper())

        handler.postDelayed({
            findNavController().navigate(R.id.action_splashScreen_to_mainScreen)
        }, 2000)
    }

    private val openLoginScreenObserver = Observer<Unit> {
        val handler = Handler(Looper.getMainLooper())

        handler.postDelayed({
            findNavController().navigate(R.id.action_splashScreen_to_loginScreen)
        }, 2000)
    }
}