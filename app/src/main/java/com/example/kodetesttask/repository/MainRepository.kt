package com.example.kodetesttask.repository

import com.example.kodetesttask.database.DatabaseDao
import com.example.kodetesttask.network.UsersRemoteDataSource
import com.example.kodetesttask.utils.performGetOperation
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class MainRepository @Inject constructor(
	private val remoteDataSource: UsersRemoteDataSource,
	private val localDataSource: DatabaseDao,
) {

	fun getUsersItems() = performGetOperation(
		{ localDataSource.getAllUser() },
		{ remoteDataSource.getAllListUsers() },
		{ localDataSource.insert(it.items) })
}

