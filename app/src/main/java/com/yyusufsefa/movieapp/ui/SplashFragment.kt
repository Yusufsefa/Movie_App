package com.yyusufsefa.movieapp.ui

import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import androidx.navigation.fragment.findNavController
import com.yyusufsefa.movieapp.R
import com.yyusufsefa.movieapp.common.BaseFragment
import com.yyusufsefa.movieapp.databinding.FragmentSplashBinding

class SplashFragment : BaseFragment<FragmentSplashBinding>(R.layout.fragment_splash) {

    override fun init() {
        val slideAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.side_slide)
        binding.splash.startAnimation(slideAnimation)

        Handler(Looper.getMainLooper()).postDelayed({
            findNavController().navigate(R.id.action_splashFragment_to_mainFragment)
        }, 3000)
    }
}
