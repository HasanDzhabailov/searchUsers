package com.example.kodetesttask.ui.users

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.kodetesttask.model.UsersList
import com.example.kodetesttask.repository.MainRepository
import com.example.kodetesttask.utils.Resource
import javax.inject.Inject

class UsersListViewModel @Inject constructor(private val repository: MainRepository) : ViewModel() {
	fun getUserListNetwork(sort:Int): LiveData<Resource<List<UsersList>>> {

		return repository.getUsersItems(sort)
	}


	fun getFilterUsersList(department: String,sort: Int): LiveData<List<UsersList>> {
		return repository.getUsersFilter(department,sort)
	}

	fun getAllUsersList(sort: Int): LiveData<List<UsersList>> {
		return repository.getAllUsersList(sort)
	}
}