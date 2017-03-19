package com.asadmshah.materialnews.storage

import android.app.Application
import java.io.File
import java.io.InputStream
import java.io.OutputStream
import javax.inject.Inject

internal class StorageImpl @Inject constructor(private val application: Application) : Storage {

    override fun file(name: String): File {
        return File(application.filesDir, name)
    }

    override fun writer(name: String): OutputStream {
        val file = File(application.filesDir, name)
        if (!file.exists()) {
            file.createNewFile()
        }
        return file.outputStream()
    }

    override fun reader(name: String): InputStream {
        val file = File(application.filesDir, name)
        return file.inputStream()
    }
}