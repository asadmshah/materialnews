package com.asadmshah.materialnews.storage

import java.io.File
import java.io.InputStream
import java.io.OutputStream

interface Storage {

    fun file(name: String): File

    fun writer(name: String): OutputStream

    fun reader(name: String): InputStream

}