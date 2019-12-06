package com.example.databinding

import android.os.Parcel
import android.os.Parcelable

// 注意接口的实现写法
class UserModel() : Parcelable {
    var name: String? = ""
    var password: String? = ""

    constructor(parcel: Parcel) : this() {
        name = parcel.readString()
        password = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(password)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UserModel> {
        override fun createFromParcel(parcel: Parcel): UserModel {
            return UserModel(parcel)
        }

        override fun newArray(size: Int): Array<UserModel?> {
            return arrayOfNulls(size)
        }
    }
}