package com.example.aplikasidonasi.view.user

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.aplikasidonasi.view.user.carddonasi.formatRupiah
import com.example.aplikasidonasi.viewmodel.DetailDonasiViewModel

@Composable
fun HalamanDetailDonasi(
    id: Int,
    onDonasiClick: (Int) -> Unit
) {

    val viewModel: DetailDonasiViewModel = viewModel()

    val detail by viewModel.detailDonasi.collectAsState()

    LaunchedEffect(id) {
        viewModel.loadDetailDonasi(id)
    }

    if (detail == null) {
        Text("Loading...")
        return
    }

    val data = detail!!

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        // ================= FOTO =================
        AsyncImage(
            model = data.foto,
            contentDescription = data.nama,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
        )

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            // ================= NAMA =================
            Text(
                text = data.nama,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            // ================= DESKRIPSI =================
            Text(
                text = data.deskripsi,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(16.dp))

            // ================= PROGRESS =================
            val progress = data.terkumpul.toFloat() / data.target.toFloat()

            LinearProgressIndicator(
                progress = progress.coerceIn(0f, 1f),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // ================= NOMINAL =================
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text("Terkumpul", color = Color.Gray)
                    Text(
                        formatRupiah(data.terkumpul),
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                }

                Column(horizontalAlignment = Alignment.End) {
                    Text("Target", color = Color.Gray)
                    Text(
                        formatRupiah(data.target),
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    onDonasiClick(data.id)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = "Donasi Sekarang",
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}