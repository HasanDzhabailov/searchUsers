package com.example.kodetesttask.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Entity(tableName = "users_list_table")
data class UsersList(
	@PrimaryKey(autoGenerate = false)
	var id:String,

	val avatarUrl:String,

	val firstName:String,

	val lastName:String,

	val userTag:String,

	val department:String,

	val position:String,

	val birthday:String,

	val phone:String,
)
