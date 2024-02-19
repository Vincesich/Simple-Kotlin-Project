package com.example.simplekotlinproject.sqlite

data class UserData(
    val id: Int,
    val firstname: String,
    val lastname: String,
    val username: String,
    val email: String,
    val phone: Int,
    val city: String,
    val password: String,
    val confirmpassword: String,
)
