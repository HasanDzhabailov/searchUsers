package com.example.kodetesttask.ui.users

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.example.kodetesttask.databinding.UserItemBinding
import com.example.kodetesttask.model.Users
import com.example.kodetesttask.model.UsersList
import java.util.*
import kotlin.collections.ArrayList

class UserListAdapter(
	private val listener: UsersItemListener,
	private val context: Context,
	var users: Users,
	var sortType: UsersListFragment.SortType,
) : RecyclerView.Adapter<UsersViewHolder>() {

	private val items = ArrayList<UsersList>()

	private fun sort(users: Users): Users {
		val usersList = Users()
		users.forEach { user -> usersList.add(user) }
		if (sortType == UsersListFragment.SortType.ALPHABET) {
			Collections.sort(usersList) { o1, o2 -> o1!!.firstName.compareTo(o2!!.firstName) }
		}
		return usersList
	}

	init {
		this.users = sort(users)
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
		val binding: UserItemBinding =
			UserItemBinding.inflate(LayoutInflater.from(context), parent, false)
		return UsersViewHolder(binding, listener)
	}

	override fun getItemCount(): Int = items.size

	override fun onBindViewHolder(holder: UsersViewHolder, position: Int) =
		holder.bind(items[position])

	fun setItems(items: ArrayList<UsersList>) {
		this.items.clear()
		this.items.addAll(items)
		notifyDataSetChanged()
	}

	interface UsersItemListener {
		fun onClickedUser(userId: String)
	}
}

class UsersViewHolder(
	private val itemBinding: UserItemBinding,
	private val listener: UserListAdapter.UsersItemListener,
) : RecyclerView.ViewHolder(itemBinding.root), View.OnClickListener {

	private lateinit var user: UsersList

	init {
		itemBinding.root.setOnClickListener(this)
	}

	override fun onClick(v: View?) {
		listener.onClickedUser(user.id)
	}

	@SuppressLint("SetTextI18n")
	fun bind(item: UsersList) {
		this.user = item
		itemBinding.fullNameTextView.text = "${item.firstName}  ${item.lastName}"
		itemBinding.userTagTextView.text = item.userTag
		itemBinding.departmentTextView.text = item.department
		Glide.with(itemBinding.root)
			.load(item.avatarUrl)
			.transform(CircleCrop())
			.into(itemBinding.avatarImageView)
	}
}
