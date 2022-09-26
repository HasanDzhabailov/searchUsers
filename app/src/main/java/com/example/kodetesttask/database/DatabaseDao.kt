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

	@Query("SELECT * from users_list_table order by firstName ASC")
	suspend fun getAllUsersWhenSearching(): List<UsersList>

	@Query("SELECT * from users_list_table WHERE department =:department ORDER by firstName ASC")
	suspend fun getUsersFilterTabsWhenSearching(department: String): List<UsersList>

	@Query(
		"""SELECT * from users_list_table ORDER by 
		   CASE WHEN :sort = 1 THEN firstName END ASC,
		   CASE WHEN :sort = 2 THEN birthday END DESC"""
	)
	fun getAllUsersFromDatabase(sort: Int): LiveData<List<UsersList>>


	@Query(
		"""SELECT * from users_list_table WHERE department =:department ORDER by 
		   CASE WHEN :sort = 1 THEN firstName END ASC, 
		   CASE WHEN :sort = 2 THEN birthday END DESC  """
	)
	fun getFilterUsersFromDatabase(department: String, sort: Int): LiveData<List<UsersList>>


	@Query("SELECT * from users_list_table WHERE id =:id")
	fun getUserById(id: String): LiveData<UsersList>


	@Query("DELETE from users_list_table")
	suspend fun clearDataFromDatabase()


	@Query(
		"""
		  SELECT *
		  FROM users_list_table
		  JOIN users_list_table_fts ON users_list_table.firstName = users_list_table_fts.firstName
		  WHERE (users_list_table.department =:department) and users_list_table_fts MATCH :query
		  ORDER BY firstName ASC
		"""
	)
	suspend fun searchUsersWhenTabsFilter(query: String, department: String): List<UsersList>


	@Query(
		"""
		  SELECT *
		  FROM users_list_table
		  JOIN users_list_table_fts ON users_list_table.firstName = users_list_table_fts.firstName
		  WHERE  users_list_table_fts MATCH :query
		  ORDER BY firstName ASC
		"""
	)
	suspend fun searchWhenAllUsers(query: String): List<UsersList>
}
