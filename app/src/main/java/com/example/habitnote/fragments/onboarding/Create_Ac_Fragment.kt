package com.example.habitnote.fragments.onboarding

import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.habitnote.R
import com.example.habitnote.data.User
import com.example.habitnote.databinding.FragmentCreateAcBinding
import com.example.habitnote.viewmodels.onboarding.Authentication_ViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class Create_Ac_Fragment : Fragment() {

    private lateinit var binding: FragmentCreateAcBinding
    private val fAuth = FirebaseAuth.getInstance()
    private val viewModel:Authentication_ViewModel by lazy { Authentication_ViewModel() }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding=FragmentCreateAcBinding.inflate(inflater,container,false)


        viewModel.authResult.observe(viewLifecycleOwner) { message ->
            message?.let {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
                binding.progressBar.visibility = View.GONE
            }
        }



        binding.apply {
            //Navigating from create account fragment to login fragment
            btnLoginHere.setOnClickListener {
                findNavController().navigate(R.id.action_create_Ac_Fragment_to_login_Fragment)
            }

            //Setting text of appbar to create account
            appbar.tvAppbar.text = "Create Account"

            //Going back to viewpager fragment on back pressed
            appbar.iv.setOnClickListener {
                findNavController().navigate(R.id.action_create_Ac_Fragment_to_viewPagerFragment)
            }

        }

        binding.apply {
            //Creating an account
            btnCreateAc.setOnClickListener {
                progressBar.visibility=View.VISIBLE
                val name=dispNameEdittxtLayout.text.toString()
                val email=emailEdittxtLayout.text.toString()
                val password=passEdittxtLayout.text.toString()
                val confirmPass=confirmPassEdittxtLayout.text.toString()

                if (inforMationValid(name, email, password, confirmPass)) {
                    lifecycleScope.launch {
                        viewModel.createUser(email, password) // Call ViewModel function
                    }
                } else {
                    Toast.makeText(requireContext(), "Invalid information", Toast.LENGTH_SHORT).show()
                    progressBar.visibility = View.GONE
                }


                //Saving user data in database
                viewModel.authResult.observe(viewLifecycleOwner) { message ->
                    if (message == "Success") {
                        viewModel.saveUserData(User(fAuth.currentUser!!.uid, name, email))
                    }
                }
            }
        }




        return binding.root
    }

    private fun inforMationValid(
        name: String,
        email: String,
        password: String,
        confirmPass: String,
    ): Boolean {
        return when {
            name.isEmpty() -> {
                binding.dispNameEdittxtLayout.error = "Enter your name"
                false
            }
            email.isEmpty() -> {
                binding.emailEdittxtLayout.error = "Enter your email"
                false
            }
            !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                binding.emailEdittxtLayout.error = "Enter a valid email"
                false
            }
            password.isEmpty() -> {
                binding.passEdittxtLayout.error = "Enter your password"
                false
            }
            password.length < 8 -> { // Check for minimum password length
                binding.passEdittxtLayout.error = "Password must be at least 8 characters long"
                false
            }
            !password.matches(Regex(".*[A-Z].*")) -> { // Check for at least one uppercase letter
                binding.passEdittxtLayout.error = "Password must contain at least one uppercase letter"
                false
            }
            !password.matches(Regex(".*[a-z].*")) -> { // Check for at least one lowercase letter
                binding.passEdittxtLayout.error = "Password must contain at least one lowercase letter"
                false
            }
            !password.matches(Regex(".*\\d.*")) -> { // Check for at least one digit
                binding.passEdittxtLayout.error = "Password must contain at least one digit"
                false
            }
            !password.matches(Regex(".*[!@#\$%^&*()_+=-].*")) -> { // Check for at least one special character
                binding.passEdittxtLayout.error = "Password must contain at least one special character"
                false
            }
            confirmPass.isEmpty() -> {
                binding.confirmPassEdittxtLayout.error = "Confirm your password"
                false
            }
            password != confirmPass -> {
                binding.confirmPassEdittxtLayout.error = "Passwords don't match"
                false
            }
            else -> true
        }
    }


}