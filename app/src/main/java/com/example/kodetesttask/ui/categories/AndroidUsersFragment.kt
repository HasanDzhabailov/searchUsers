package com.example.kodetesttask.ui.categories

import com.example.kodetesttask.ui.users.UsersListFragment

class AndroidUsersFragment: UsersListFragment() {

    override fun getCategory(): String {
        return "android"
    }
}