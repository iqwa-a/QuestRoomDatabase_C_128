package com.example.questroomdatabase.ui.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class UpdateMhsViewModel(
    saveStateHandle: SavedStateHandle,
    private val repositoryMhs: RepositoryMhs
) : ViewModel() {

    var updateUiState by mutableStateOf(MhsUiState())

    private val _nim: String = checkNotNull(saveStateHandle[DestinasiDetail.NIM]) // Ganti DestinasiEdit menjadi DestinasiDetail

    init {
        viewModelScope.launch {
            updateUiState = repositoryMhs.getMhs(_nim)
                .filterNotNull()
                .first()
                .toUiStateMhs()
        }
    }
