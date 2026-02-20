package com.example.sneakerlink

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainMenu : AppCompatActivity() {
    private  lateinit var btnCategories: Button
    private lateinit var btnLinks: Button
    private  lateinit var btnNewItem: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)


        btnCategories = findViewById(R.id.catbtn)

        btnCategories.setOnClickListener {
            val intent = Intent(this, AllCategories::class.java)
            startActivity(intent)
        }

        btnLinks = findViewById(R.id.slbtn)

        btnLinks.setOnClickListener {
            val intent = Intent(this, LinksPage::class.java)
            startActivity(intent)
        }

        btnNewItem = findViewById(R.id.nwbtn)
        btnNewItem.setOnClickListener {
            val intent = Intent(this, New_Item::class.java)
            startActivity(intent)
        }
    }
}