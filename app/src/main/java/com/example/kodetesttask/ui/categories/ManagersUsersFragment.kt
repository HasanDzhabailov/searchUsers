package com.example.kodetesttask.ui.categories

import com.example.kodetesttask.ui.users.UsersListFragment

class ManagersUsersFragment : UsersListFragment() {

	override fun getCategory(): String {
		return "management"
	}
}