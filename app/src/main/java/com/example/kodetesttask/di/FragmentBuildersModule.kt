package com.example.kodetesttask.di



import com.example.kodetesttask.ui.categories.*
import com.example.kodetesttask.ui.home.HomeFragment
import com.example.kodetesttask.ui.profiledetail.ProfileDetailFragment
import com.example.kodetesttask.ui.users.UsersListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {
	@ContributesAndroidInjector
	abstract fun contributeHomeFragmentFragment(): HomeFragment

	@ContributesAndroidInjector
	abstract fun contributeProfileDetailFragment(): ProfileDetailFragment

	@ContributesAndroidInjector
	abstract fun contributeUsersListFragment(): UsersListFragment

	//CategoryFragment
	@ContributesAndroidInjector
	abstract fun contributeAnalystFragment(): AnalystsUsersFragment

	@ContributesAndroidInjector
	abstract fun contributeAndroidFragment(): AndroidUsersFragment

	@ContributesAndroidInjector
	abstract fun contributeBackendFragment(): BackendersUsersFragment

	@ContributesAndroidInjector
	abstract fun contributeBackOfficerFragment(): BackOfficerUsersFragment

	@ContributesAndroidInjector
	abstract fun contributeDesignerFragment(): DesignersUsersFragment

	@ContributesAndroidInjector
	abstract fun contributeFrontendFragment(): FrontendersUsersFragment

	@ContributesAndroidInjector
	abstract fun contributeHRFragment(): HRsUsersFragment

	@ContributesAndroidInjector
	abstract fun contributeIosFragment(): iOSUsersFragment

	@ContributesAndroidInjector
	abstract fun contributeManagersFragment(): ManagersUsersFragment

	@ContributesAndroidInjector
	abstract fun contributePrFragment(): PRsUsersFragment

	@ContributesAndroidInjector
	abstract fun contributeQAFragment(): QAUsersFragment

	@ContributesAndroidInjector
	abstract fun contributeSupportsFragment(): SupportsUsersFragment
}