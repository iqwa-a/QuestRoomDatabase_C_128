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
// Data class untuk menyimpan UI state
data class MhsUiState(
    val mahasiswaEvent: MahasiswaEvent = MahasiswaEvent(), // Data form saat ini
    val isEntryValid: FormErrorState = FormErrorState(), // Status validasi
    val snackbarMessage: String? = null // Pesan yang akan ditampilkan di UI
)

// Data class untuk validasi form
data class FormErrorState(
    val nim: String? = null, // Error message untuk NIM
    val nama: String? = null, // Error message untuk Nama
    val jenisKelamin: String? = null, // Error message untuk Jenis Kelamin
    val alamat: String? = null, // Error message untuk Alamat
    val kelas: String? = null, // Error message untuk Kelas
    val angkatan: String? = null, // Error message untuk Angkatan
) {
    // Fungsi untuk memeriksa apakah semua input valid
    fun isValid(): Boolean {
        return nim == null && nama == null && jenisKelamin == null &&
                alamat == null && kelas == null && angkatan == null
    }
}
// Fungsi ekstensi untuk mengubah MahasiswaEvent menjadi Mahasiswa (entity)
fun MahasiswaEvent.toMahasiswaEntity(): Mahasiswa = Mahasiswa(
    nim = nim,
    nama = nama,
    jenisKelamin = jenisKelamin,
    alamat = alamat,
    kelas = kelas,
    angkatan = angkatan
)

// Data class untuk menangani input form
data class MahasiswaEvent(
    val nim: String = "", // Input NIM
    val nama: String = "", // Input Nama
    val jenisKelamin: String = "", // Input Jenis Kelamin
    val alamat: String = "", // Input Alamat
    val kelas: String = "", // Input Kelas
    val angkatan: String = "" // Input Angkatan
)