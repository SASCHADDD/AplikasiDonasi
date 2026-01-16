package com.example.aplikasidonasi.view.user

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.aplikasidonasi.view.user.carddonasi.DonasiCard
import com.example.aplikasidonasi.view.user.topbar.HomeTopBar
import com.example.aplikasidonasi.viewmodel.HomeViewModel


@Composable
fun HomeScreenUser(
    paddingValues: PaddingValues,
    onItemClick: (Int) -> Unit,
    onProfileClick: () -> Unit
) {
    val viewModel: HomeViewModel = viewModel()

    val listDonasi by viewModel.filteredDonasi.collectAsState()
    val loading by viewModel.loading.collectAsState()
    val query by viewModel.searchQuery.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchTempatDonasi()
    }

    if (loading) {
        LoadingScreen()
        return
    }

    LazyColumn(
        contentPadding = PaddingValues(
            top = paddingValues.calculateTopPadding() + 72.dp, // ruang topbar
            bottom = paddingValues.calculateBottomPadding() + 16.dp // ruang navbar
        )
    ) {
        items(listDonasi) { donasi ->
            DonasiCard(
                tempatDonasi = donasi,
                onItemClick = { onItemClick(donasi.id) }
            )
        }
    }
}
