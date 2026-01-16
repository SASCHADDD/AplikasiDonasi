package com.example.aplikasidonasi.view.user.uploadfile

import android.content.Context
import android.net.Uri
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.io.FileOutputStream

object FileHelper {

    fun uriToMultipart(
        context: Context,
        uri: Uri,
        key: String
    ): MultipartBody.Part {

        val inputStream = context.contentResolver.openInputStream(uri)
            ?: throw IllegalArgumentException("Cannot open input stream")

        val file = File(context.cacheDir, "upload_${System.currentTimeMillis()}.jpg")

        FileOutputStream(file).use { output ->
            inputStream.copyTo(output)
        }

        val requestBody = file
            .asRequestBody("image/*".toMediaType())

        return MultipartBody.Part.createFormData(
            name = key,
            filename = file.name,
            body = requestBody
        )
    }
}