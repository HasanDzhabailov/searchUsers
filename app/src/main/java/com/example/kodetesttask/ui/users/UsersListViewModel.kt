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
		return repository.loadAndSaveData(sort)
	}

	fun getFilterUsersFromDatabase(department: String, sort: Int): LiveData<List<UsersList>> {
		return repository.getFilterUsersFromDatabase(department, sort)
	}

	fun getAllUsersFromDatabase(sort: Int): LiveData<List<UsersList>> {
		return repository.getAllUsersFromDatabase(sort)
	}

	fun getSearchUsers(query: Editable?, department: String) {
		viewModelScope.launch {
			if (query.isNullOrBlank()) {
				when (department) {
					"all" -> {
						repository.getAllUsersWhenSearching().let { _searchResults.postValue(it) }
					}
					else -> {
						repository.getUsersFilterTabsWhenSearching(department).let { _searchResults.postValue(it) }
					}
				}
			} else {
				when (department) {
					"all" -> {
						repository.searchWhenAllUsers("*$query*").let { _searchResults.postValue(it) }
					}
					else -> {
						repository.searchUsersWhenTabsFilter("*$query*", department).let {
							_searchResults.postValue(it)
						}
					}
				}
			}
		}
	}
}
