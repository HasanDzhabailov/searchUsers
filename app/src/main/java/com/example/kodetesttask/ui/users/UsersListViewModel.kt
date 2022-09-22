package com.example.kodetesttask.ui.users

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.kodetesttask.model.UsersList
import com.example.kodetesttask.repository.MainRepository
import javax.inject.Inject

class UsersListViewModel @Inject constructor(private val repository: MainRepository) : ViewModel() {
	val userList = repository.getUsersItems()

	fun getFilterUsersList(department: String): LiveData<List<UsersList>> {
		return repository.getUsersFilter(department)
	}

	fun getAllUsersList(): LiveData<List<UsersList>> {
		return repository.getAllUsersList()
	}
}