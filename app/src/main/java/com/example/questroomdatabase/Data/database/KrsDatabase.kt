package com.example.questroomdatabase.Data.database

import androidx.room.Database
import androidx.room.RoomDatabase


// Mendefinisikan database Room dengan entitas Mahasiswa
@Database(entities = [Mahasiswa::class], version = 1, exportSchema = false)
abstract class KrsDatabase : RoomDatabase()