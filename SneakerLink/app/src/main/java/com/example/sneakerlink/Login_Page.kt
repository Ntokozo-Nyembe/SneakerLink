package com.example.sneakerlink

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.sneakerlink.databinding.ActivityLoginPageBinding
import com.google.firebase.auth.FirebaseAuth

class Login_Page : AppCompatActivity() {

    private lateinit var binding:ActivityLoginPageBinding
    private lateinit var firebaseAuth: FirebaseAuth
    /*private  lateinit var btnLogin: Button
    private lateinit var btnSignUp: Button
    private  lateinit var Username: EditText
    private  lateinit var Password: EditText
    private  lateinit var sneakerdb: SneakerDB*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

         binding  = ActivityLoginPageBinding.inflate(layoutInflater)
         setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        binding.textView15.setOnClickListener {
            val intent = Intent(this, SignUpPage:: class.java)
            startActivity(intent)
        }
        binding.libtn.setOnClickListener {
            val usernameTxt = binding.editTextTextPersonName3.text.toString()
            val passwordTxt = binding.editTextTextPersonName4.text.toString()


            if(usernameTxt.isNotEmpty() && passwordTxt.isNotEmpty()){

                    firebaseAuth.signInWithEmailAndPassword(usernameTxt, passwordTxt).addOnCompleteListener {
                        if(it.isSuccessful){
                            val intent = Intent(this, MainMenu::class.java )
                            startActivity(intent)

                        }else{
                            Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }
            }else{
                Toast.makeText(this, "Some Fields are Empty!!", Toast.LENGTH_SHORT).show()
            }
        }
    }

   /*override fun onStart(){
        super.onStart()

        if(firebaseAuth.currentUser !=null){
            val intent = Intent(this, MainMenu::class.java )
            startActivity(intent)
        }
    }*/
}