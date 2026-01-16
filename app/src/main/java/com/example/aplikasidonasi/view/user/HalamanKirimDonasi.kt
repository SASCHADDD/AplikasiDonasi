package com.example.aplikasidonasi.view.user

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.aplikasidonasi.R
import com.example.aplikasidonasi.view.user.download.DownloadHelper
import com.example.aplikasidonasi.view.user.rupiah.formatRupiahInput
import com.example.aplikasidonasi.viewmodel.KirimDonasiViewModel


@Composable
fun HalamanKirimDonasi(
    donasiId: Int,
    onBackClick: () -> Unit,
    token: String?
) {
    if (token == null) {
        Text("Silakan login ulang")
        return
    }
    val context = LocalContext.current
    val viewModel: KirimDonasiViewModel = viewModel()

    val nominal by viewModel.nominal.collectAsState()
    val bukti by viewModel.buktiTransfer.collectAsState()

    val imagePicker =
        rememberLauncherForActivityResult(
            ActivityResultContracts.GetContent()
        ) { uri ->
            uri?.let { viewModel.setBuktiTransfer(it) }
        }

    val primaryColor = Color(0xFF1D4ED8)   // Biru utama
    val backgroundColor = Color(0xFFF9FAFB)
    val success by viewModel.success.collectAsState()
    LaunchedEffect(success) {
        if (success) {
            onBackClick()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()) // SCROLL
                .padding(16.dp)
        ) {

            // ===== JUDUL =====
            Text(
                text = "Kirim Donasi",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = primaryColor
            )

            Spacer(Modifier.height(16.dp))

            // ===== INPUT NOMINAL =====
            OutlinedTextField(
                value = formatRupiahInput(nominal),
                onValueChange = { input ->
                    val clean = input.filter { it.isDigit() }
                    viewModel.updateNominal(clean)
                },
                label = { Text("Nominal Donasi") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = primaryColor,
                    focusedLabelColor = primaryColor
                )
            )

            Spacer(Modifier.height(20.dp))

            // ===== QRIS =====
            Text(
                "Scan / Unduh QRIS",
                fontWeight = FontWeight.SemiBold,
                color = Color.DarkGray
            )

            Spacer(Modifier.height(8.dp))

            Image(
                painter = painterResource(id = R.drawable.qris_demo),
                contentDescription = "QRIS Demo",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(12.dp))
            )

            Spacer(Modifier.height(10.dp))

            Button(
                onClick = {
                    DownloadHelper.saveQrisFromDrawable(
                        context = context,
                        drawableRes = R.drawable.qris_demo,
                        fileName = "qris_donasi_$donasiId.png"
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = primaryColor
                )
            ) {
                Text("Unduh QRIS")
            }

            Spacer(Modifier.height(28.dp))

            // ===== UPLOAD BUKTI =====
            Text(
                "Upload Bukti Transfer",
                fontWeight = FontWeight.SemiBold,
                color = Color.DarkGray
            )

            Spacer(Modifier.height(8.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color.White)
                    .border(
                        width = 1.5.dp,
                        color = primaryColor,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .clickable {
                        imagePicker.launch("image/*")
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Tap di sini untuk upload bukti transfer",
                    color = primaryColor,
                    fontWeight = FontWeight.Medium
                )
            }

            bukti?.let {
                Spacer(Modifier.height(14.dp))
                AsyncImage(
                    model = it,
                    contentDescription = "Bukti Transfer",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                        .clip(RoundedCornerShape(12.dp))
                )
            }

            Spacer(Modifier.height(36.dp))

            // ===== TOMBOL =====
            Button(
                onClick = onBackClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFE5E7EB)
                )
            ) {
                Text("Kembali", color = Color.DarkGray)
            }

            Spacer(Modifier.height(12.dp))

            Button(
                onClick = {
                    if (token == null) return@Button

                    viewModel.submitDonasi(
                        context = context,
                        tempatId = donasiId,
                        token = token
                    )
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Kirim Donasi")
            }
        }

            Spacer(Modifier.height(48.dp))
        }
    }