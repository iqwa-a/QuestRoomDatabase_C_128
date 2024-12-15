package com.example.questroomdatabase.ui.viewmodel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory

object penyediaViewModel {
    val Factory = viewModelFactory {
        initializer {
            MahasiswaViewModel(
                KrsApp().containerApp.repositoryMhs
            )
        }

        initializer {
            HomeMhsViewModel(
                KrsApp().containerApp.repositoryMhs
            )
        }

        initializer {
            DetailMhsViewModel(
                createSavedStateHandle(),
                KrsApp().containerApp.repositoryMhs
            )
        }

        initializer {
            UpdateMhsViewModel(
                createSavedStateHandle(),
                KrsApp().containerApp.repositoryMhs
            )
        }
    }
}
fun CreationExtras.KrsApp(): KrsApp =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as KrsApp)