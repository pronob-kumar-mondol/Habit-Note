package com.example.habitnote.onboarding

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.habitnote.R

class SplashFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val v= inflater.inflate(R.layout.fragment_splash, container, false)

        Handler(Looper.getMainLooper()).postDelayed({

            // Use NavOptions to remove SplashFragment from the back stack
            val navOptions=NavOptions.Builder()
                .setPopUpTo(R.id.splashFragment,true)
                .build()

            findNavController().navigate(R.id.action_splashFragment_to_viewPagerFragment,null,navOptions)
        },3000)


        return v
    }

}