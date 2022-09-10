package com.example.kodetesttask.network

import com.example.kodetesttask.model.entity.UsersItemsEntity
import com.example.kodetesttask.network.NetworkUrl.Companion.URL_USERS
import retrofit2.Call
import retrofit2.Response


import retrofit2.http.GET

interface ApiService {

	@GET(URL_USERS)
	suspend fun getListUsers(): Response<UsersItemsEntity>
}

