package com.example.aplikasidonasi.view.user.topbar

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.aplikasidonasi.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar(
    query: String,
    onQueryChange: (String) -> Unit,
    onProfileClick: () -> Unit
) {

    TopAppBar(
        title = {
            SearchBar(
                query = query,
                onQueryChange = onQueryChange,
                onSearch = { },
                active = false,
                onActiveChange = { },
                placeholder = { Text("Cari donasi...") },
                modifier = Modifier.fillMaxWidth()
            ) {}
        },
        actions = {
            IconButton(onClick = onProfileClick) {
                Image(
                    painter = painterResource(R.drawable.user),
                    contentDescription = "Profile",
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                )
            }
        }
    )
}