package com.example.aplikasidonasi.viewmodel

import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aplikasidonasi.repository.DonasiRepository
import com.example.aplikasidonasi.view.user.uploadfile.FileHelper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class KirimDonasiViewModel : ViewModel() {
    private val _success = MutableStateFlow(false)
    val success: StateFlow<Boolean> = _success
    private val repository = DonasiRepository()

    private val _nominal = MutableStateFlow("")
    val nominal: StateFlow<String> = _nominal

    private val _buktiTransfer = MutableStateFlow<Uri?>(null)
    val buktiTransfer: StateFlow<Uri?> = _buktiTransfer

    fun updateNominal(value: String) {
        _nominal.value = value
    }

    fun setBuktiTransfer(uri: Uri) {
        _buktiTransfer.value = uri
    }

    fun submitDonasi(
        context: Context,
        tempatId: Int,
        token: String

    ) {
        val buktiUri = _buktiTransfer.value ?: return
        val nominalInt = _nominal.value.toIntOrNull() ?: return

        viewModelScope.launch {
            try {
                val multipart = FileHelper.uriToMultipart(
                    context = context,
                    uri = buktiUri,
                    key = "bukti_transfer"
                )

                repository.submitDonasi(
                    token = token,
                    tempatId = tempatId,
                    nominal = nominalInt,
                    bukti = multipart
                )
            } catch (e: Exception) {
                e.printStackTrace()
            }
            _success.value = true
        }
    }
}