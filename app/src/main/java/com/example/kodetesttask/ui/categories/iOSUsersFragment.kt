package com.example.kodetesttask.ui.categories

import com.example.kodetesttask.ui.users.UsersListFragment

class iOSUsersFragment: UsersListFragment() {

    override fun getCategory(): String {
        return "ios"
    }
}