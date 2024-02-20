package com.example.simplekotlinproject.screens

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.simplekotlinproject.R
import com.example.simplekotlinproject.sqlite.DatabaseHelper

class Login: AppCompatActivity() {

    private lateinit var db: DatabaseHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        db = DatabaseHelper(this)

        val loginbutton = findViewById<Button>(R.id.loginbutton)
        loginbutton.setOnClickListener{
            val emailEditText = findViewById<EditText>(R.id.emailinput)
            val email = emailEditText.text.toString()
            val passwordEditText = findViewById<EditText>(R.id.password)
            val password = passwordEditText.text.toString()
            val user = db.getUser(email, password)
            if (user != null){
                Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Invalid credentials!", Toast.LENGTH_SHORT).show()
            }
            val intent = Intent(this, Dashboard::class.java)
            startActivity(intent)
        }
    }
}