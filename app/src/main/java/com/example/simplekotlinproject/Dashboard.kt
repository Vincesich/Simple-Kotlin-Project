package com.example.simplekotlinproject

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class Dashboard : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dashboard)
        val gotouserslistbutton = findViewById<Button>(R.id.gotouserslistbutton)
        gotouserslistbutton.setOnClickListener{
            val intent = Intent(this, UsersList::class.java)
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