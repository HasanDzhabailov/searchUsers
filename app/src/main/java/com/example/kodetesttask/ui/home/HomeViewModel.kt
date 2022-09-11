package com.example.kodetesttask.ui.home


import android.util.Log
import androidx.lifecycle.*
import com.example.kodetesttask.database.DatabaseDao
import com.example.kodetesttask.model.UsersList
import com.example.kodetesttask.model.entity.UserItemEntity
import com.example.kodetesttask.model.entity.UsersItemsEntity

import com.example.kodetesttask.repository.MainRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
private val repository: MainRepository
) : ViewModel() {

val userList = repository.getUsersItems()

	fun getUsersFilter(department:String): LiveData<List<UsersList>> {
		return repository.getUsersFilter(department)
	}


}