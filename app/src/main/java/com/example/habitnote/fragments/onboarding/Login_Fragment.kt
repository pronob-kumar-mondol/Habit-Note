package com.example.habitnote.fragments.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.habitnote.R
import com.example.habitnote.databinding.FragmentLoginBinding
import com.example.habitnote.viewmodels.onboarding.Authentication_ViewModel
import kotlinx.coroutines.launch

class Login_Fragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    private val viewModel by lazy { Authentication_ViewModel() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding=FragmentLoginBinding.inflate(inflater,container,false)


        viewModel.authResult.observe(viewLifecycleOwner){msg->
            msg?.let {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
                binding.progressBar.visibility = View.GONE
            }
        }


        //Navigating from login fragment to create account fragment and forgot password fragment
        binding.apply {
            btnCreateAnAcHere.setOnClickListener {
                findNavController().navigate(R.id.action_login_Fragment_to_create_Ac_Fragment)
            }
            forgotPass.setOnClickListener {
                findNavController().navigate(R.id.action_login_Fragment_to_forgotPass_Fragment)
            }

            //Setting text of appbar to login
            appbar.tvAppbar.text = "Login"

            //Navigating from login fragment to view pager fragment on back pressed
            appbar.iv.setOnClickListener {
                findNavController().navigate(R.id.action_login_Fragment_to_viewPagerFragment)
            }

        }


        //Login
        binding.apply {
            btnLogin.setOnClickListener {
                progressBar.visibility=View.VISIBLE
                val email=emailEdittxtLayout.text.toString()
                val password=passEdittxtLayout.text.toString()

                if (email.isNotEmpty() && password.isNotEmpty()) {
                    lifecycleScope.launch {
                        viewModel.loginUser(email, password)
                    }
                }
            }
        }





        return binding.root
    }



}