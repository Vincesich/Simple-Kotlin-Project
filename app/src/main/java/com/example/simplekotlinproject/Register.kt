package com.example.simplekotlinproject

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.simplekotlinproject.sqlite.DatabaseHelper
import com.example.simplekotlinproject.sqlite.UserData


class Register : AppCompatActivity() {

    private lateinit var db: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register)

        db = DatabaseHelper(this)

        val firstNameEditText = findViewById<EditText>(R.id.firstname)
        val lastNameEditText = findViewById<EditText>(R.id.lastname)
        val usernameEditText = findViewById<EditText>(R.id.username)
        val emailEditText = findViewById<EditText>(R.id.emailinput)
        val cityEditText = findViewById<EditText>(R.id.city)
        val phoneNumberEditText = findViewById<EditText>(R.id.phonenumber)
        val passwordEditText = findViewById<EditText>(R.id.password)
        val confirmPasswordEditText = findViewById<EditText>(R.id.confirmpassword)

        val registerbutton = findViewById<Button>(R.id.registerbutton)
        registerbutton.setOnClickListener{
            val firstName = firstNameEditText.text.toString()
            val lastName = lastNameEditText.text.toString()
            val username = usernameEditText.text.toString()
            val email = emailEditText.text.toString()
            val city = cityEditText.text.toString()
            val phoneNumber = phoneNumberEditText.text.toString().toIntOrNull() ?: 0
            val password = passwordEditText.text.toString()
            val confirmPassword = confirmPasswordEditText.text.toString()

            if (password != confirmPassword) {
                Toast.makeText(this, "Passwords do not match!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val user = UserData(0, firstName, lastName, username, email, phoneNumber, city, password, confirmPassword)
            db.addUser(user)
            Toast.makeText(this, "User registered successfully!", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, Dashboard::class.java)
            startActivity(intent)
        }
    }
}