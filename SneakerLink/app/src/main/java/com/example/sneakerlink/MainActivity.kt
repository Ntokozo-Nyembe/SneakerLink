package com.example.sneakerlink

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    private  lateinit var btnLogin: Button
    private lateinit var btnSignUp: Button
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnLogin = findViewById(R.id.button2)
        btnLogin.setOnClickListener {
            val intent = Intent(this, Login_Page::class.java )
            startActivity(intent)

        }

        btnSignUp = findViewById(R.id.button3)
        btnSignUp.setOnClickListener {
            val intent = Intent(this, SignUpPage::class.java )
            startActivity(intent)
        }
    }
}