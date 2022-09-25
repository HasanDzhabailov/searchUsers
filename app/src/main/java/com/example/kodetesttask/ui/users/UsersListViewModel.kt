package com.example.kodetesttask.ui.users

import android.text.Editable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kodetesttask.model.UsersList
import com.example.kodetesttask.repository.MainRepository
import com.example.kodetesttask.utils.Resource
import javax.inject.Inject
import kotlinx.coroutines.launch

class UsersListViewModel @Inject constructor(private val repository: MainRepository) : ViewModel() {
	private val _searchResults = MutableLiveData<List<UsersList>>()
	val searchResults: LiveData<List<UsersList>>
		get() = _searchResults

	fun getUserListNetwork(sort: Int): LiveData<Resource<List<UsersList>>> {

		return repository.getUsersItems(sort)
	}

	fun getFilterUsersList(department: String, sort: Int): LiveData<List<UsersList>> {
		return repository.getUsersFilter(department, sort)
	}

	fun getAllUsersList(sort: Int): LiveData<List<UsersList>> {
		return repository.getAllUsersList(sort)
	}

	fun getSearchUsers(query: Editable?, department: String) {
		viewModelScope.launch {
			if (query.isNullOrBlank()) {
				when (department) {
					"all" -> {
						repository.allSearch().let { _searchResults.postValue(it) }
					}
					else -> {
						repository.searchFilterTabs(department).let { _searchResults.postValue(it) }
					}
				}
			} else {
				when (department) {
					"all" -> {
						repository.searchAllUsers("*$query*").let { _searchResults.postValue(it) }
					}
					else -> {
						repository.getSearchUsers("*$query*", department).let {
							_searchResults.postValue(it)
						}
					}
				}
			}
		}
	}
}
