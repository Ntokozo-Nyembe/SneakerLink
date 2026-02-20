package com.example.sneakerlink

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView

class AllCategories : AppCompatActivity() {
    private lateinit var categoryContainer: LinearLayout
    private lateinit var btnAddCategory: TextView
    private lateinit var categoryProgressBar: ProgressBar // Declare as a member variable
    private lateinit var goalTextView: TextView // Declare as a member variable
    companion object {
        private const val NEW_ITEM_REQUEST_CODE = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_categories)

        categoryContainer = findViewById(R.id.categoryContainer)
        btnAddCategory = findViewById(R.id.toCate)

        btnAddCategory.setOnClickListener {
            // Handle the "Add Category" button click
            // Show the Add Category dialog or navigate to the createCategory activity
            // ...

            // Example: Navigate to the createCategory activity
            val intent = Intent(this, createCategory::class.java)
            startActivity(intent)
        }

        val intent = getIntent()
        val getCateName = intent.getStringExtra("categoryNameEditText")
        val getTarget = intent.getIntExtra("targetEditText", 0) // Retrieve as an integer
        val isNewCategory = intent.getBooleanExtra("isNewCategory", false)

        if (isNewCategory) {
            val inflater = LayoutInflater.from(this)
            val newCategoryView = inflater.inflate(R.layout.category_layout, null)

            val categoryTextView = newCategoryView.findViewById<TextView>(R.id.categoryTextView)
            goalTextView = newCategoryView.findViewById<TextView>(R.id.goalTextView) // Add the goalTextView
            categoryProgressBar = newCategoryView.findViewById<ProgressBar>(R.id.progressBar) // Update the member variable
            val btnAddNewItem = newCategoryView.findViewById<Button>(R.id.btnAddNewItem)

            categoryTextView.text = getCateName
            goalTextView.text = "Goal: $getTarget" // Set the goal text
            categoryProgressBar.max = getTarget

            btnAddNewItem.setOnClickListener {
                // Handle the "Add New Item" button click
                // Show the Add Item dialog or navigate to the New_Item activity
                val intent = Intent(this, New_Item::class.java)
                startActivityForResult(intent, NEW_ITEM_REQUEST_CODE)
            }

            categoryContainer.addView(newCategoryView) // Add the new category to the container layout
        }

        // ...
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == NEW_ITEM_REQUEST_CODE && resultCode == RESULT_OK) {
            val currentProgress = categoryProgressBar.progress
            val newProgress = currentProgress + 1
            categoryProgressBar.progress = newProgress
        }
    }

    // ...
}
