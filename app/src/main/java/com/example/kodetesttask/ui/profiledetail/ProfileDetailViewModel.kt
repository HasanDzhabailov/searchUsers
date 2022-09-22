package com.example.kodetesttask.ui.profiledetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.example.kodetesttask.model.UsersList
import com.example.kodetesttask.repository.MainRepository
import javax.inject.Inject

class ProfileDetailViewModel @Inject constructor(private val repository: MainRepository) :
	ViewModel() {
	private val _id = MutableLiveData<String>()
	private val _character = _id.switchMap { id ->
		repository.getUserId(id)
	}
	val character: LiveData<UsersList> = _character

	fun start(id: String) {
		_id.value = id
	}
}