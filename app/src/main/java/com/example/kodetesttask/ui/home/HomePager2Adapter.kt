package com.example.kodetesttask.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.kodetesttask.Consts.ARG_TAG
import com.example.kodetesttask.Consts.tabValue
import com.example.kodetesttask.ui.categories.*
import com.example.kodetesttask.ui.users.UsersListFragment

class HomePager2Adapter(fragment: Fragment): FragmentStateAdapter(fragment) {
	override fun getItemCount(): Int {
		return 13
	}

	override fun createFragment(position: Int): Fragment {
				return when (position){
					0 -> UsersListFragment()
					1 -> AnalystsUsersFragment()
					2 -> AndroidUsersFragment()
					3 -> BackendersUsersFragment()
					4 -> BackOfficerUsersFragment()
					5 -> DesignersUsersFragment()
					6 -> FrontendersUsersFragment()
					7 -> HRsUsersFragment()
					8 -> iOSUsersFragment()
					9 -> ManagersUsersFragment()
					10 -> PRsUsersFragment()
					11 -> QAUsersFragment()
					12 -> SupportsUsersFragment()
					else -> UsersListFragment()
				}
	}


}