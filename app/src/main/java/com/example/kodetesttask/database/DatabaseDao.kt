package com.example.kodetesttask.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.kodetesttask.model.UsersList
import com.example.kodetesttask.model.entity.UserItemEntity

@Dao
interface DatabaseDao {
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insert(users: List<UsersList>)

	@Query("SELECT * from users_list_table")
	fun getAllUser(): LiveData<List<UsersList>>

	@Query("DELETE from users_list_table")
	fun clearAll()
}