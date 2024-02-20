package com.example.simplekotlinproject.screens

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.simplekotlinproject.R
import com.example.simplekotlinproject.sqlite.DatabaseHelper


class Dashboard : AppCompatActivity() {
    private lateinit var db: DatabaseHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dashboard)
        db = DatabaseHelper(this)

        val gotouserslistbutton = findViewById<Button>(R.id.gotouserslistbutton)
        gotouserslistbutton.setOnClickListener {
            val userList = db.getAllUsers() // Fetch list of all users from SQLite database

            val intent = Intent(this, UsersList::class.java).apply {
                putExtra("userList", ArrayList(userList)) // Pass the list of users to the UsersList activity
            }
            startActivity(intent)
        }
        val gotoaboutbutton = findViewById<Button>(R.id.gotoaboutbutton)
        gotoaboutbutton.setOnClickListener{
            val intent = Intent(this, About::class.java)
            startActivity(intent)
        }
        val gotohelpbutton = findViewById<Button>(R.id.gotohelpbutton)
        gotohelpbutton.setOnClickListener{
            val intent = Intent(this, Help::class.java)
            startActivity(intent)
        }
    }
}