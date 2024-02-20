package com.example.simplekotlinproject.sqlite

import android.os.Parcel
import android.os.Parcelable

data class UserData(
    val id: Int,
    val firstname: String,
    val lastname: String,
    val username: String,
    val email: String,
    val phone: Int,
    val city: String,
    val password: String,
    val confirmpassword: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(firstname)
        parcel.writeString(lastname)
        parcel.writeString(username)
        parcel.writeString(email)
        parcel.writeInt(phone)
        parcel.writeString(city)
        parcel.writeString(password)
        parcel.writeString(confirmpassword)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UserData> {
        override fun createFromParcel(parcel: Parcel): UserData {
            return UserData(parcel)
        }

        override fun newArray(size: Int): Array<UserData?> {
            return arrayOfNulls(size)
        }
    }
}

