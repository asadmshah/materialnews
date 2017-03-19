package com.asadmshah.materialnews.models

import android.os.Parcel
import android.os.Parcelable

data class Publisher(val name: String, val code: String, val color: Int) : Parcelable {

    companion object {
        @JvmField
        @Suppress
        val CREATOR = object : Parcelable.Creator<Publisher> {
            override fun newArray(size: Int): Array<Publisher?> {
                return arrayOfNulls(size)
            }

            override fun createFromParcel(source: Parcel): Publisher {
                return Publisher(
                        source.readString(),
                        source.readString(),
                        source.readInt())
            }
        }
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(name)
        dest.writeString(code)
        dest.writeInt(color)
    }

    override fun describeContents(): Int {
        return 0
    }
}