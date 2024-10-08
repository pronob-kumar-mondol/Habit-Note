package com.example.habitnote.viewmodels.onboarding

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.habitnote.data.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch

class Authentication_ViewModel:ViewModel() {

    private val fAuth= FirebaseAuth.getInstance()
    private lateinit var firestore: FirebaseFirestore

    private val _authResult=MutableLiveData<String?>()
    val authResult:LiveData<String?> get() = _authResult

    //Create user in firebase
    fun createUser(email:String,password:String) {

        viewModelScope.launch {
            fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener {
                if (it.isSuccessful){
                    Log.d("TAG", "createUser: Success")
                    _authResult.value="Success"
                }else{
                    Log.d("TAG", it.exception?.message.toString())
                    _authResult.value=it.exception?.message.toString()
                }
            }
        }
    }

    //Save User Data in Database

    fun saveUserData(user: User){
        firestore=FirebaseFirestore.getInstance()
        viewModelScope.launch {
            firestore.collection("users").document(user.uId).set(user)
        }
    }


    //Login User
    fun loginUser(email:String,password:String){
        viewModelScope.launch {
            fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener {
                if (it.isSuccessful){
                    Log.d("TAG", "loginUser: Success")
                    _authResult.value="Success"
                }else{
                    Log.d("TAG", it.exception?.message.toString())
                    _authResult.value=it.exception?.message.toString()
                }
            }
        }
    }


}