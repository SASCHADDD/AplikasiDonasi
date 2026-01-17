package com.example.aplikasidonasi.view.admin

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.aplikasidonasi.view.user.carddonasi.DonasiCard
import com.example.aplikasidonasi.viewmodel.HomeViewModel

@Composable
fun AdminHomeScreen(
    paddingValues: PaddingValues,
    onAddClick: () -> Unit,
    onItemClick: (Int) -> Unit
) {
    val viewModel: HomeViewModel = viewModel()
    val listDonasi by viewModel.filteredDonasi.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchTempatDonasi()
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddClick
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Tambah Tempat Donasi"
                )
            }
        }
    ) { innerPadding ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(paddingValues),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(listDonasi) { donasi ->
                DonasiCard(
                    tempatDonasi = donasi,
                    onItemClick = { onItemClick(donasi.id) }
                )
            }
        }
    }
}
