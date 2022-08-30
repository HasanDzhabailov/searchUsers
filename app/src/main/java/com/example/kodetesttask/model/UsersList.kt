package com.example.kodetesttask.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "users_list_table")
data class UsersList(
	@PrimaryKey(autoGenerate = false)
	var id:String,

	@ColumnInfo(name =  "avatar_url" )
	val avatarUrl:String,

	@ColumnInfo(name =  "first_name" )
	val firstName:String,

	@ColumnInfo(name =  "last_name" )
	val lastName:String,

	@ColumnInfo(name =  "user_tag" )
	val userTag:String,

	@ColumnInfo(name =  "department" )
	val department:String,

	@ColumnInfo(name =  "position" )
	val position:String,

	@ColumnInfo(name =  "birthday" )
	val birthday:String,

	@ColumnInfo(name =  "phone" )
	val phone:String,
):Parcelable
