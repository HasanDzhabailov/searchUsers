package com.example.kodetesttask.di



import com.example.kodetesttask.ui.home.HomeFragment
import com.example.kodetesttask.ui.profiledetail.ProfileDetailFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {
	@ContributesAndroidInjector
	abstract fun contributeHomeFragmentFragment(): HomeFragment

	@ContributesAndroidInjector
	abstract fun contributeAddExpensesFragment(): ProfileDetailFragment

}