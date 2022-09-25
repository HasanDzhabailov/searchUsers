package com.example.kodetesttask.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.kodetesttask.model.UsersList
import com.example.kodetesttask.model.UsersListFts

@Database(entities = [UsersList::class,UsersListFts::class], version = 1, exportSchema = false)

abstract class DatabaseApp : RoomDatabase() {
	abstract fun DatabaseDao(): DatabaseDao
}