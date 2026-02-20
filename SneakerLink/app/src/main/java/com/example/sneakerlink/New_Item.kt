package com.example.sneakerlink
import android.app.ProgressDialog
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.sneakerlink.databinding.ActivityCategoryCreateBinding
import com.google.firebase.auth.FirebaseAuth

class New_Item : AppCompatActivity() {


    private lateinit var imageView: ImageView
    private lateinit var uploadButton: Button
    private lateinit var nameEditText: EditText
    private lateinit var modelEditText: EditText
    private lateinit var dateEditText: EditText
    private lateinit var addItemButton: Button
    private val REQUEST_IMAGE_CAPTURE = 100
    private lateinit var binding: ActivityCategoryCreateBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var progressDialog: ProgressDialog

    private var progress = 0
    private var achievements = mutableSetOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_item)

        imageView = findViewById(R.id.uploadPic)
        uploadButton = findViewById(R.id.Btn_Upload)
        nameEditText = findViewById(R.id.editTextTextPersonName3)
        modelEditText = findViewById(R.id.editTextTextPersonName4)
        dateEditText = findViewById(R.id.editTextTextPersonName6)
        addItemButton = findViewById(R.id.button5)

        uploadButton.setOnClickListener {
            val takePicIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            try {
                startActivityForResult(takePicIntent, REQUEST_IMAGE_CAPTURE)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(this, "Error: " + e.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        }

        addItemButton.setOnClickListener {
            // Handle the "Add item" button click
            // ...

            updateProgress()
            checkAchievements()

            setResult(RESULT_OK)
            finish()
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            imageView.setImageBitmap(imageBitmap)
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    private fun saveItemInformation() {
        val name = nameEditText.text.toString()
        val model = modelEditText.text.toString()
        val date = dateEditText.text.toString()

        // Save the information to storage or perform desired actions
        // For example, you can use SharedPreferences to store the data
        val sharedPreferences = getSharedPreferences("ItemData", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("Name", name)
        editor.putString("Model", model)
        editor.putString("Date", date)
        editor.apply()
    }

    private fun updateProgress() {
        progress++
        checkAchievements() // Call checkAchievements after updating progress
    }

    private fun checkAchievements() {
        if (progress == 1) {
            unlockAchievement("Starter")
        } else if (progress == 3) {
            unlockAchievement("Collector")
        } else if (progress == 10) {
            unlockAchievement("Packrat")
        }
    }


    private fun unlockAchievement(achievement: String) {
        if (!achievements.contains(achievement)) {
            achievements.add(achievement)
            showAchievementUnlockedDialog(achievement)
        }
    }

    private fun showAchievementUnlockedDialog(achievement: String) {
        AlertDialog.Builder(this)
            .setTitle("Achievement Unlocked")
            .setMessage("Congratulations! You unlocked the achievement: $achievement")
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

}

