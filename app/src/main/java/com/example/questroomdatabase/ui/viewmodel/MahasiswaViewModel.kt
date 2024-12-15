package com.example.questroomdatabase.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

// ViewModel untuk mengelola data Mahasiswa dan logika UI
class MahasiswaViewModel(private val repositoryMhs: RepositoryMhs) : ViewModel() {
    // State untuk menyimpan data yang akan digunakan di UI
    var uiState by mutableStateOf(MhsUiState())
        private set
}
