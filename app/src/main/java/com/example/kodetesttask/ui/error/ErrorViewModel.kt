package com.example.kodetesttask.ui.error

import androidx.lifecycle.ViewModel

import com.example.kodetesttask.repository.MainRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class ErrorViewModel @Inject constructor(private val repository: MainRepository):ViewModel() {
	fun clearDataFromDatabase(){
		CoroutineScope(Dispatchers.IO).launch {
			repository.clearDataFromDatabase()
		}

	}
}