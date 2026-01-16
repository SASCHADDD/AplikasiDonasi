package com.example.aplikasidonasi.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aplikasidonasi.model.TempatDonasi
import com.example.aplikasidonasi.repository.DonasiRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val repository = DonasiRepository()

    private val _tempatDonasi = MutableStateFlow<List<TempatDonasi>>(emptyList())
    val tempatDonasi: StateFlow<List<TempatDonasi>> = _tempatDonasi

    fun fetchTempatDonasi() {
        viewModelScope.launch {
            repository.fetchListTempatDonasi()
        }
    }
    // LIST & LOADING
    val listDonasi = repository.listDonasi
    val loading = repository.loading

    init {
        viewModelScope.launch {
            repository.getListTempatDonasi()
        }
    }

    // SEARCH QUERY
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }


    // FILTER DONASI

    val filteredDonasi: StateFlow<List<TempatDonasi>> =
        combine(listDonasi, _searchQuery) { list, query ->
            if (query.isBlank()) {
                list
            } else {
                list.filter {
                    it.nama.contains(query, ignoreCase = true)
                }
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )
}