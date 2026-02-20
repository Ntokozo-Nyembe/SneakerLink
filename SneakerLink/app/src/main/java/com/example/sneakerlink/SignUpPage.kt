package com.example.sneakerlink

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.sneakerlink.databinding.ActivitySignUpPageBinding
import com.google.firebase.auth.FirebaseAuth

class SignUpPage : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpPageBinding
    private lateinit var firebaseAuth: FirebaseAuth
    /*private lateinit var btnSignUp: Button
    private lateinit var sneakerDB: SneakerDB
    private lateinit var firstName: EditText
    private lateinit var lastName: EditText
    private lateinit var username: EditText
    private lateinit var password : EditText
    private lateinit var conPassword: EditText*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignUpPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        binding.textView11.setOnClickListener {
            val intent = Intent(this, Login_Page::class.java )
            startActivity(intent)
        }

        //signup binding
        binding.button4.setOnClickListener {
            val firstNameTxt = binding.editTextTextPersonName.text.toString()
            val lastNameTxt = binding.editTextTextPersonName2.text.toString()
            val usernameTxt = binding.editTextTextPersonName5.text.toString()
            val passwordTxt = binding.editTextTextPassword.text.toString()
            val conPasswordText = binding.editTextTextPassword2.text.toString()

            if(firstNameTxt.isNotEmpty() && lastNameTxt.isNotEmpty() && usernameTxt.isNotEmpty() && passwordTxt.isNotEmpty() && conPasswordText.isNotEmpty()){
                if(passwordTxt == conPasswordText){

                    firebaseAuth.createUserWithEmailAndPassword(usernameTxt, passwordTxt).addOnCompleteListener {
                        if(it.isSuccessful){
                            val intent = Intent(this, Login_Page::class.java )
                            startActivity(intent)

                        }else{
                            Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }

                }else{
                    Toast.makeText(this, "Password is not matching", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this, "Some Fields are Empty!!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}