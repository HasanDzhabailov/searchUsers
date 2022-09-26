package com.example.kodetesttask.repository

import androidx.lifecycle.LiveData
import com.example.kodetesttask.database.DatabaseDao
import com.example.kodetesttask.model.UsersList
import com.example.kodetesttask.network.UsersRemoteDataSource
import com.example.kodetesttask.utils.performGetOperation
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRepository
@Inject
constructor(
	private val remoteDataSource: UsersRemoteDataSource,
	private val localDataSource: DatabaseDao,
) {

	fun loadAndSaveData(sort: Int) =
		performGetOperation(
			{ localDataSource.getAllUsersFromDatabase(sort) },
			{ remoteDataSource.getAllListUsers() },
			{ localDataSource.insert(it.items) }
		)

	suspend fun clearDataFromDatabase(){
		return localDataSource.clearDataFromDatabase()
	}
	fun getFilterUsersFromDatabase(deportment: String, sort: Int): LiveData<List<UsersList>> {
		return localDataSource.getFilterUsersFromDatabase(deportment, sort)
	}

	fun getUserById(id: String): LiveData<UsersList> {
		return localDataSource.getUserById(id)
	}

	fun getAllUsersFromDatabase(sort: Int): LiveData<List<UsersList>> {
		return localDataSource.getAllUsersFromDatabase(sort)
	}

	suspend fun getAllUsersWhenSearching(): List<UsersList> {
		return localDataSource.getAllUsersWhenSearching()
	}

	suspend fun getUsersFilterTabsWhenSearching(department: String): List<UsersList> {
		return localDataSource.getUsersFilterTabsWhenSearching(department)
	}

	suspend fun searchWhenAllUsers(query: String): List<UsersList> {
		return localDataSource.searchWhenAllUsers(query)
	}

	suspend fun searchUsersWhenTabsFilter(query: String, department: String): List<UsersList> {
		return localDataSource.searchUsersWhenTabsFilter(query, department)
	}
}
