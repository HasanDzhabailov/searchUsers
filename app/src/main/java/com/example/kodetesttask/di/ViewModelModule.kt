package com.example.kodetesttask.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kodetesttask.ui.error.ErrorViewModel
import com.example.kodetesttask.ui.home.HomeViewModel
import com.example.kodetesttask.ui.profiledetail.ProfileDetailViewModel
import com.example.kodetesttask.ui.users.UsersListViewModel
import com.example.kodetesttask.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelModule {

	@Binds
	@IntoMap
	@ViewModelKey(HomeViewModel::class)
	abstract fun bindHomeViewModel(homeViewModel: HomeViewModel): ViewModel

	@Binds
	@IntoMap
	@ViewModelKey(ProfileDetailViewModel::class)
	abstract fun bindProfileDetailViewModel(
		profileDetailViewModel: ProfileDetailViewModel
	): ViewModel

	@Binds
	@IntoMap
	@ViewModelKey(UsersListViewModel::class)
	abstract fun bindUsersListViewModel(usersListViewModel: UsersListViewModel): ViewModel

	@Binds
	@IntoMap
	@ViewModelKey(ErrorViewModel::class)
	abstract fun binErrorViewModel(errorViewModel: ErrorViewModel): ViewModel

	@Binds abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
