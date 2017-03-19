package com.asadmshah.materialnews.models

import android.os.Parcel
import android.os.Parcelable
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

data class Article @JsonCreator constructor(
        @JsonProperty("author") val author: String,
        @JsonProperty("title") val title: String,
        @JsonProperty("description") val description: String,
        @JsonProperty("url") val postUrl: String,
        @JsonProperty("urlToImage") val imageUrl: String,
        @JsonProperty("publishedAt") val date: Date?) : Parcelable {

    companion object {

        @JvmField
        @Suppress("unused")
        val CREATOR = object : Parcelable.Creator<Article> {
            override fun newArray(size: Int): Array<Article?> {
                return arrayOfNulls(size)
            }

            override fun createFromParcel(source: Parcel): Article {
                val author = source.readString()
                val title = source.readString()
                val description = source.readString()
                val postUrl = source.readString()
                val imageUrl = source.readString()
                val dtRaw = source.readLong()
                val date = if (dtRaw > 0) Date(dtRaw) else null

                return Article(author, title, description, postUrl, imageUrl, date)
            }
        }
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(author)
        dest.writeString(title)
        dest.writeString(description)
        dest.writeString(postUrl)
        dest.writeString(imageUrl)
        dest.writeLong(date?.time ?: -1)
    }

    override fun describeContents(): Int {
        return 0
    }
}