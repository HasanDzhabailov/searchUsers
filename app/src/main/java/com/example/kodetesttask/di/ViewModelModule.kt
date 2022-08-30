package com.example.kodetesttask.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

import com.example.kodetesttask.di.ViewModelKey
import com.example.kodetesttask.ui.home.HomeViewModel
import com.example.kodetesttask.ui.profiledetail.ProfileDetailViewModel
import com.example.kodetesttask.viewmodel.ViewModelFactory

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class  ViewModelModule {

	@Binds
	@IntoMap
	@ViewModelKey(HomeViewModel::class)
	abstract fun bindHomeViewModel(homeViewModel: HomeViewModel): ViewModel

	@Binds
	@IntoMap
	@ViewModelKey(ProfileDetailViewModel::class)
	abstract fun bindAddExpensesViewModel(addExpensesViewModel: ProfileDetailViewModel): ViewModel



	@Binds
	abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}