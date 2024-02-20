package com.example.simplekotlinproject.screens

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.simplekotlinproject.R

class Splash: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash)

        val gotologinpagebutton = findViewById<Button>(R.id.gotologinpagebutton)
        gotologinpagebutton.setOnClickListener{
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }
        val gotoregisterpagebutton = findViewById<Button>(R.id.gotoregisterpagebutton)
        gotoregisterpagebutton.setOnClickListener{
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
        }
    }
}