@file:Suppress("DEPRECATION")

package com.example.simplekotlinproject

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.simplekotlinproject.sqlite.UserData

class UsersList : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.userslist)

        val userList = intent.getParcelableArrayListExtra<UserData>("userList")

        val textView: TextView = findViewById(R.id.userslist)
        textView.text = userList?.joinToString("\n") { userData ->
            "Name: ${userData.firstname} ${userData.lastname}\n" +
                    "Username: ${userData.username}\n" +
                    "Email: ${userData.email}\n" +
                    "City: ${userData.city}\n" +
                    "Phone: ${userData.phone}\n" +
                    "------------------------------------"
        } ?: "No users found"
    }
}

