package com.example.aplikasidonasi.viewmodel

import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aplikasidonasi.apiservice.ApiConfig
import com.example.aplikasidonasi.repository.DonasiRepository
import com.example.aplikasidonasi.view.user.uploadfile.FileHelper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

class AdminTambahTempatDonasiViewModel : ViewModel() {

    private val repository = DonasiRepository()

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    private val _success = MutableStateFlow(false)
    val success: StateFlow<Boolean> = _success

    fun submit(
        context: Context,
        token: String,
        nama: String,
        deskripsi: String,
        target: String,
        fotoUri: Uri
    ) {
        viewModelScope.launch {
            try {
                _loading.value = true

                val namaBody = nama.toRequestBody("text/plain".toMediaType())
                val deskBody = deskripsi.toRequestBody("text/plain".toMediaType())
                val targetBody = target.toRequestBody("text/plain".toMediaType())

                val fotoPart = FileHelper.uriToMultipart(
                    context = context,
                    uri = fotoUri,
                    key = "foto"
                )

                repository.tambahTempatDonasi(
                    token = token,
                    nama = namaBody,
                    deskripsi = deskBody,
                    target = targetBody,
                    foto = fotoPart
                )

                _success.value = true

            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _loading.value = false
            }
        }
    }
}
