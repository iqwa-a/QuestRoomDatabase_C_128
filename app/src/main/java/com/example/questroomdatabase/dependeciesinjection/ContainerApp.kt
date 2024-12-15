package com.example.questroomdatabase.dependeciesinjection

import android.content.Context
import com.example.questroomdatabase.Data.database.KrsDatabase
import com.example.questroomdatabase.repository.LocalRepositoryMhs
import com.example.questroomdatabase.repository.RepositoryMhs

interface InterfaceContainerApp {
    val repositoryMhs: RepositoryMhs
}

class ContainerApp(private val context: Context) : InterfaceContainerApp {
    override val repositoryMhs: RepositoryMhs by lazy {
        LocalRepositoryMhs(KrsDatabase.getDatabase(context).mahasiswaDao())
    }
}