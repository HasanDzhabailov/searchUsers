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
	private val localDataSource: DatabaseDao
) {



	fun getUsersItems(sort: Int) =
		performGetOperation(
			{ localDataSource.getAllUser(sort) },
			{ remoteDataSource.getAllListUsers() },
			{ localDataSource.insert(it.items) }
		)

	fun getUsersFilter(deportment: String, sort: Int): LiveData<List<UsersList>> {
		return localDataSource.getUsersFilter(deportment, sort)
	}

	fun getUserId(id: String): LiveData<UsersList> {
		return localDataSource.getUserId(id)
	}

	fun getAllUsersList(sort: Int): LiveData<List<UsersList>> {
		return localDataSource.getAllUser(sort)
	}
	suspend fun getSearchUsers(query: String, department: String): List<UsersList> {
		return localDataSource.searchUsers(query, department)
	}
	suspend fun allSearch(): List<UsersList> {
		return localDataSource.allSearch()
	}

	suspend fun searchFilterTabs(department: String): List<UsersList> {
		return localDataSource.searchFilterTabs(department)
	}

	suspend fun searchAllUsers(query: String): List<UsersList> {
		return localDataSource.searchUsersAll(query)
	}
}
