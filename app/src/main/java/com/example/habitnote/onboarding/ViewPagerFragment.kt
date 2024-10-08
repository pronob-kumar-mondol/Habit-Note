package com.example.habitnote.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.habitnote.R
import com.example.habitnote.databinding.FragmentViewPagerBinding
import com.example.habitnote.onboarding.screen.FirstScreen
import com.example.habitnote.onboarding.screen.SecondScreen
import com.example.habitnote.onboarding.screen.ThirdScreen
import com.google.android.material.appbar.AppBarLayout

class ViewPagerFragment : Fragment() {

    private lateinit var binding:FragmentViewPagerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding=FragmentViewPagerBinding.inflate(inflater,container,false)

        val fragmentList= arrayListOf<Fragment>(
            FirstScreen(),
            SecondScreen(),
            ThirdScreen()
        )

        val adapter=ViewPagerAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle
        )

        binding.apply {
            viewPager.adapter=adapter
        }

        //Navigating from viewpager to create account fragment and login fragment
        binding.apply {
            createAccountBtn.setOnClickListener {
                findNavController().navigate(R.id.action_viewPagerFragment_to_create_Ac_Fragment)
            }
            loginBtn.setOnClickListener {
                findNavController().navigate(R.id.action_viewPagerFragment_to_login_Fragment)
            }
        }




        return binding.root
    }



}