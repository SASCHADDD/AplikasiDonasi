package com.example.aplikasidonasi.view.user

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.aplikasidonasi.viewmodel.RiwayatDonasiViewModel

@Composable
fun HalamanRiwayatDonasi(
    token: String,
    paddingValues: PaddingValues,
    viewModel: RiwayatDonasiViewModel = viewModel()
) {
    val data by viewModel.riwayat.observeAsState(emptyList())

    LaunchedEffect(Unit) {
        viewModel.loadRiwayat(token)
    }

    LazyColumn(
        modifier = Modifier.padding(paddingValues)
    ) {
        items(data) { item ->
            Card(
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxWidth(),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = item.nama_tempat, fontWeight = FontWeight.Bold)
                    Text(text = "Nominal: Rp ${item.nominal}")
                    Text(text = "Tanggal: ${item.created_at}")
                }
            }
        }
    }
}