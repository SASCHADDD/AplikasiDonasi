package com.example.aplikasidonasi.view.user.download

import android.content.ContentValues
import android.content.Context
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import androidx.annotation.DrawableRes
import java.io.OutputStream

object DownloadHelper {

    fun saveQrisFromDrawable(
        context: Context,
        @DrawableRes drawableRes: Int,
        fileName: String
    ) {
        val bitmap = BitmapFactory.decodeResource(
            context.resources,
            drawableRes
        )

        val resolver = context.contentResolver

        val contentValues = ContentValues().apply {
            put(MediaStore.Images.Media.DISPLAY_NAME, fileName)
            put(MediaStore.Images.Media.MIME_TYPE, "image/png")

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                put(
                    MediaStore.Images.Media.RELATIVE_PATH,
                    Environment.DIRECTORY_PICTURES + "/QRIS Donasi"
                )
                put(MediaStore.Images.Media.IS_PENDING, 1)
            }
        }

        val imageUri = resolver.insert(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            contentValues
        )

        imageUri?.let { uri ->
            val outputStream: OutputStream? =
                resolver.openOutputStream(uri)

            outputStream?.use {
                bitmap.compress(
                    android.graphics.Bitmap.CompressFormat.PNG,
                    100,
                    it
                )
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                contentValues.clear()
                contentValues.put(MediaStore.Images.Media.IS_PENDING, 0)
                resolver.update(uri, contentValues, null, null)
            }
        }
    }
}