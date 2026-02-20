package com.example.sneakerlink

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SneakerDB(context: Context): SQLiteOpenHelper(context, "SneakerLink", null, 1) {
    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL("Create Table UsersData(First_Name VARCHAR(35), Last_Name VARCHAR(35), Username VARCHAR(30) Primary key, Password VARCHAR(64))")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

    fun insertData(

        First_Name: String, Last_Name: String, Username: String, Password: String

    )
    {
        val db: SQLiteDatabase? = this.writableDatabase
        val values: ContentValues = ContentValues()
        values.put("First_Name", First_Name)
        values.put("Last_Name", Last_Name)
        values.put("Username", Username)
        values.put(" Password", Password)
        if (db != null) {
            db.insert("UsersData", null, values)
            db.close()
        }

    }

    fun checkUserInfor(Username: String, Password: String): Boolean{
        val p0 = this.writableDatabase
        val query = "Select * from UsersData where Username = '$Username' and Password = '$Password'"
        val cursor = p0.rawQuery(query, null)
        if (cursor.count <= 0){
            cursor.close()
            return false
        }
        cursor.close()
        return true
    }
}