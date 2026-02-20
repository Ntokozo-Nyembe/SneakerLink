package com.example.sneakerlink

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.swiperefreshlayout.widget.CircularProgressDrawable.ProgressDrawableSize
import com.example.sneakerlink.databinding.ActivityCategoryCreateBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.example.sneakerlink.AllCategories

class createCategory : AppCompatActivity() {
    private lateinit var categoryNameEditText: EditText
    private lateinit var targetEditText: EditText
    private lateinit var createBtn: Button
    private lateinit var binding: ActivityCategoryCreateBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var progressDialog: ProgressDialog
    private lateinit var ref: Firebase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryCreateBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val btnViewCategory: Button = binding.btnViewCategory

        btnViewCategory.setOnClickListener {
            val intent = Intent(this, AllCategories::class.java)
            startActivity(intent)
        }
        categoryNameEditText = binding.editCateName
        targetEditText = binding.editGoal
        createBtn = binding.cateButton

        createBtn.setOnClickListener{
            firebaseAuth = FirebaseAuth.getInstance()
            progressDialog = ProgressDialog(this)
            progressDialog.setTitle("Please wait..")
            progressDialog.setCanceledOnTouchOutside(false)
            binding.editCateName.setOnClickListener {
                validateData()

            }
            createBtn.setOnClickListener{
                val getCateName = categoryNameEditText.text.toString()
                val getTarget = targetEditText.text.toString()

                val intent = Intent(this, AllCategories::class.java)
                intent.putExtra("categoryNameEditText", getCateName)
                intent.putExtra("targetEditText", getTarget.toInt()) // Convert the goal to an integer
                intent.putExtra("isNewCategory", true) // Indicate that a new category is being created
                startActivity(intent)
            }

        }

        validateData()

    }

    private var getName = ""
    private var getTarget = ""

    private fun validateData() {
        getName = binding.editCateName.text.toString().trim()
        getTarget = binding.editGoal.text.toString().trim()

        if(getName.isEmpty() && getTarget.isEmpty()){
            Toast.makeText(this, "Fill all fields", Toast.LENGTH_SHORT).show()
        }else{
            addCategoryFirebase()
        }
    }

    private fun addCategoryFirebase() {
        getName = binding.editCateName.text.toString().trim()
        getTarget = binding.editGoal.text.toString().trim()
        progressDialog.show()
      //  val key =
        val timestamp = System.currentTimeMillis()
        val hashMap=HashMap<String, Any>()
        hashMap["id"] = "$timestamp"
        hashMap["Name"] = getName
        hashMap["Target"] = getTarget
        hashMap["uid"] = "${firebaseAuth.uid}"


        FirebaseDatabase.getInstance().getReference("Categories").child("$timestamp").setValue(hashMap)
            .addOnSuccessListener{
                progressDialog.dismiss()
                Toast.makeText(this, "Added Successfully", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener{e->
                progressDialog.dismiss()
                Toast.makeText(this, "Failed to add due to ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }

}
