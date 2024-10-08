package com.example.habitnote.fragments.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.habitnote.R
import com.example.habitnote.databinding.FragmentForgotPassBinding

class ForgotPass_Fragment : Fragment() {

    private lateinit var binding: FragmentForgotPassBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentForgotPassBinding.inflate(inflater, container, false)

        return binding.root
    }

}