package com.example.questroomdatabase.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

// ViewModel untuk mengelola data Mahasiswa dan logika UI
class MahasiswaViewModel(private val repositoryMhs: RepositoryMhs) : ViewModel() {
    // State untuk menyimpan data yang akan digunakan di UI
    var uiState by mutableStateOf(MhsUiState())
        private set

    // Fungsi untuk menyimpan data ke database
    fun saveData() {
        val currentEvent = uiState.mahasiswaEvent
        if (validateField()) { // Validasi input form
            viewModelScope.launch { // Operasi database dilakukan di coroutine
                try {
                    // Menyimpan data ke database melalui repository
                    repositoryMhs.insertMhs(currentEvent.toMahasiswaEntity())
                    uiState = uiState.copy(
                        snackbarMessage = "Data berhasil disimpan", // Pesan sukses
                        mahasiswaEvent = MahasiswaEvent(), // Reset input form
                        isEntryValid = FormErrorState() // Reset validasi
                    )
                } catch (e: Exception) {
                    e.printStackTrace() // Debugging jika terjadi kesalahan
                    uiState = uiState.copy(
                        snackbarMessage = "Data gagal disimpan: ${e.message}" // Pesan error
                    )
                }
            }
        } else {
            uiState = uiState.copy(
                snackbarMessage = "Input tidak valid. Periksa kembali data." // Validasi gagal
            )
        }
    }
}
