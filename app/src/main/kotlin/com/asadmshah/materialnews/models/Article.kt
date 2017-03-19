package com.asadmshah.materialnews.models

import android.os.Parcel
import android.os.Parcelable
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

@JsonIgnoreProperties(ignoreUnknown = true)
data class Article @JsonCreator constructor(
        @JsonProperty("title", defaultValue = "") val title: String,
        @JsonProperty("urlToImage", defaultValue = "") val imageUrl: String,
        @JsonProperty("publishedAt") val date: Date?) : Parcelable {

    companion object {

        @JvmField
        @Suppress("unused")
        val CREATOR = object : Parcelable.Creator<Article> {
            override fun newArray(size: Int): Array<Article?> {
                return arrayOfNulls(size)
            }

            override fun createFromParcel(source: Parcel): Article {
                val title = source.readString()
                val imageUrl = source.readString()
                val dtRaw = source.readLong()
                val date = if (dtRaw > 0) Date(dtRaw) else null

                return Article(title, imageUrl, date)
            }
        }
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(title)
        dest.writeString(imageUrl)
        dest.writeLong(date?.time ?: -1)
    }

    override fun describeContents(): Int {
        return 0
    }
}