package com.example.simplekotlinproject.sqlite

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        val createUserTableQuery = ("CREATE TABLE $TABLE_USERS ("
                + "$KEY_ID INTEGER PRIMARY KEY,"
                + "$KEY_FIRST_NAME TEXT,"
                + "$KEY_LAST_NAME TEXT,"
                + "$KEY_USERNAME TEXT,"
                + "$KEY_EMAIL TEXT,"
                + "$KEY_PHONE INTEGER,"
                + "$KEY_CITY TEXT,"
                + "$KEY_PASSWORD TEXT,"
                + "$KEY_CONFIRM_PASSWORD TEXT)")
        db.execSQL(createUserTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_USERS")
        onCreate(db)
    }

    fun addUser(user: UserData) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(KEY_FIRST_NAME, user.firstname)
        values.put(KEY_LAST_NAME, user.lastname)
        values.put(KEY_USERNAME, user.username)
        values.put(KEY_EMAIL, user.email)
        values.put(KEY_PHONE, user.phone)
        values.put(KEY_CITY, user.city)
        values.put(KEY_PASSWORD, user.password)
        values.put(KEY_CONFIRM_PASSWORD, user.confirmpassword)
        db.insert(TABLE_USERS, null, values)
        db.close()
    }

    fun getUser(email: String, password: String): UserData? {
        val db = this.readableDatabase
        val query = "SELECT * FROM $TABLE_USERS WHERE $KEY_EMAIL = ? AND $KEY_PASSWORD = ?"
        val cursor = db.rawQuery(query, arrayOf(email, password))
        val user: UserData? = if (cursor.moveToFirst()) {
            val idIndex = cursor.getColumnIndex(KEY_ID)
            val firstNameIndex = cursor.getColumnIndex(KEY_FIRST_NAME)
            val lastNameIndex = cursor.getColumnIndex(KEY_LAST_NAME)
            val usernameIndex = cursor.getColumnIndex(KEY_USERNAME)
            val emailIndex = cursor.getColumnIndex(KEY_EMAIL)
            val phoneIndex = cursor.getColumnIndex(KEY_PHONE)
            val cityIndex = cursor.getColumnIndex(KEY_CITY)
            val passwordIndex = cursor.getColumnIndex(KEY_PASSWORD)
            val confirmPasswordIndex = cursor.getColumnIndex(KEY_CONFIRM_PASSWORD)

            if (idIndex != -1 && firstNameIndex != -1 && lastNameIndex != -1 &&
                usernameIndex != -1 && emailIndex != -1 && phoneIndex != -1 &&
                cityIndex != -1 && passwordIndex != -1 && confirmPasswordIndex != -1) {

                val id = cursor.getInt(idIndex)
                val firstName = cursor.getString(firstNameIndex)
                val lastName = cursor.getString(lastNameIndex)
                val username = cursor.getString(usernameIndex)
                val emailValue = cursor.getString(emailIndex)
                val phone = cursor.getInt(phoneIndex)
                val city = cursor.getString(cityIndex)
                val passwordValue = cursor.getString(passwordIndex) // Renamed to avoid shadowing
                val confirmPassword = cursor.getString(confirmPasswordIndex)
                UserData(id, firstName, lastName, username, emailValue, phone, city, passwordValue, confirmPassword)
            } else {
                null
            }
        } else {
            null
        }
        cursor.close()
        db.close()
        return user
    }
    fun getAllUsers(): List<UserData> {
        val userList = mutableListOf<UserData>()
        val db = this.readableDatabase
        val query = "SELECT * FROM $TABLE_USERS"
        val cursor = db.rawQuery(query, null)
        cursor.use {
            while (it.moveToNext()) {
                val idIndex = it.getColumnIndex(KEY_ID)
                val firstNameIndex = it.getColumnIndex(KEY_FIRST_NAME)
                val lastNameIndex = it.getColumnIndex(KEY_LAST_NAME)
                val usernameIndex = it.getColumnIndex(KEY_USERNAME)
                val emailIndex = it.getColumnIndex(KEY_EMAIL)
                val phoneIndex = it.getColumnIndex(KEY_PHONE)
                val cityIndex = it.getColumnIndex(KEY_CITY)
                val passwordIndex = it.getColumnIndex(KEY_PASSWORD)
                val confirmPasswordIndex = it.getColumnIndex(KEY_CONFIRM_PASSWORD)

                // Check if any column index is -1
                if (idIndex == -1 || firstNameIndex == -1 || lastNameIndex == -1 ||
                    usernameIndex == -1 || emailIndex == -1 || phoneIndex == -1 ||
                    cityIndex == -1 || passwordIndex == -1 || confirmPasswordIndex == -1) {
                    // Log an error or throw an exception or continue to next iteration
                    Log.e("DatabaseHelper", "One or more columns are missing from the cursor.")
                    return@use
                }


                val id = it.getInt(idIndex)
                val firstName = it.getString(firstNameIndex)
                val lastName = it.getString(lastNameIndex)
                val username = it.getString(usernameIndex)
                val email = it.getString(emailIndex)
                val phone = it.getInt(phoneIndex)
                val city = it.getString(cityIndex)
                val password = it.getString(passwordIndex)
                val confirmPassword = it.getString(confirmPasswordIndex)
                val user = UserData(id, firstName, lastName, username, email, phone, city, password, confirmPassword)
                userList.add(user)
            }
        }
        db.close()
        return userList
    }


    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "UsersDB"
        private const val TABLE_USERS = "users"
        private const val KEY_ID = "id"
        private const val KEY_FIRST_NAME = "first_name"
        private const val KEY_LAST_NAME = "last_name"
        private const val KEY_USERNAME = "username"
        private const val KEY_EMAIL = "email"
        private const val KEY_PHONE = "phone"
        private const val KEY_CITY = "city"
        private const val KEY_PASSWORD = "password"
        private const val KEY_CONFIRM_PASSWORD = "confirm_password"
    }
}
