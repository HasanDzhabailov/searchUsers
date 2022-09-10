package com.example.kodetesttask.model.entity

import com.squareup.moshi.Json

data class UserItemEntity(
	@Json(name = "id") val id: String,
	@Json(name = "avatarUrl") val avatarUrl: String,
	@Json(name = "firstName") val firstName: String,
	@Json(name = "lastName") val lastName: String,
	@Json(name = "userTag") val userTag: String,
	@Json(name = "department") val department: String,
	@Json(name = "position") val position: String,
	@Json(name = "birthday") val birthday: String,
	@Json(name = "phone") val phone: String,
)
