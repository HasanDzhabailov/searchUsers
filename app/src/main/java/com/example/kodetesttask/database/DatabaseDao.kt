package com.example.kodetesttask.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.kodetesttask.model.UsersList
import kotlinx.coroutines.flow.Flow

@Dao
interface DatabaseDao {
	@Insert(onConflict = OnConflictStrategy.IGNORE)
	suspend fun insert(Users:UsersList)

	@Query("SELECT * from users_list_table")
	fun getAllUser(): Flow<List<UsersList>>
	
	@Query("DELETE from users_list_table")
	fun clearAll()
}