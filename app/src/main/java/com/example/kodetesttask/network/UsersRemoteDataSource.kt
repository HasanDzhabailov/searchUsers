package com.example.kodetesttask.network

import javax.inject.Inject

class UsersRemoteDataSource @Inject constructor(
	private val apiService: ApiService,
) : BaseDataSource() {
	suspend fun getAllListUsers() = getResult { apiService.getListUsers() }
}