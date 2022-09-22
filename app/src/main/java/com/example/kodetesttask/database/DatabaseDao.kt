package com.example.kodetesttask.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.kodetesttask.model.UsersList

@Dao
interface DatabaseDao {
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insert(users: List<UsersList>)

	@Query("SELECT * from users_list_table")
	fun getAllUser(): LiveData<List<UsersList>>

	@Query("SELECT * from users_list_table WHERE department =:department")
	fun getUsersFilter(department: String):LiveData<List<UsersList>>

	@Query("SELECT * from users_list_table WHERE id =:id")
	fun getUserId(id:String):LiveData<UsersList>

	@Query("DELETE from users_list_table")
	fun clearAll()
}