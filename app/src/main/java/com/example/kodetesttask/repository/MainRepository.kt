package com.example.kodetesttask.repository

import androidx.lifecycle.LiveData
import com.example.kodetesttask.database.DatabaseDao
import com.example.kodetesttask.model.UsersList
import com.example.kodetesttask.network.UsersRemoteDataSource
import com.example.kodetesttask.utils.performGetOperation
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class MainRepository @Inject constructor(
	private val remoteDataSource: UsersRemoteDataSource,
	private val localDataSource: DatabaseDao
) {

	fun getUsersItems() = performGetOperation(
		{ localDataSource.getAllUser() },
		{ remoteDataSource.getAllListUsers() },
		{ localDataSource.insert(it.items) })

	fun getUsersFilter(deportment: String): LiveData<List<UsersList>> {
		return localDataSource.getUsersFilter(deportment)
	}

	fun getUserId(id: String): LiveData<UsersList> {
		return localDataSource.getUserId(id)
	}

	fun getAllUsersList(): LiveData<List<UsersList>> {
		return localDataSource.getAllUser()
	}
}

