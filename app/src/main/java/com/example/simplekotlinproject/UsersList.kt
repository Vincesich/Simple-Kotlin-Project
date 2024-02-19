package com.example.simplekotlinproject

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.simplekotlinproject.sqlite.UserData
import com.example.simplekotlinproject.UserListAdapeter

class UsersList : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.userslist)

       // val userList = intent.getParcelableArrayListExtra<UserData>("userList")

       // val TextView: TextView = findViewById(R.id.userslist)
       // recyclerView.adapter = UserListAdapter(userList ?: arrayListOf())
      //  recyclerView.layoutManager = LinearLayoutManager(this)
    }
}

