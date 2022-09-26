package com.example.kodetesttask.model

import androidx.room.Entity
import androidx.room.Fts4

@Fts4(contentEntity = UsersList::class)
@Entity(tableName = "users_list_table_fts")
data class UsersListFts(var firstName: String, var lastName: String)