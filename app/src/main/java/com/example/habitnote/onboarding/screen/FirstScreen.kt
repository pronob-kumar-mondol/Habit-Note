package com.example.habitnote.onboarding.screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.habitnote.databinding.FragmentFirstScreenBinding


class FirstScreen : Fragment() {

    lateinit var binding: FragmentFirstScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding=FragmentFirstScreenBinding.inflate(inflater,container,false)




        return binding.root
    }


}