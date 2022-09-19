package com.example.kodetesttask.ui.users

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.example.kodetesttask.databinding.UserItemBinding
import com.example.kodetesttask.model.UsersList

class UserListAdapter(private val listener: UsersItemListener) : RecyclerView.Adapter<UsersViewHolder>() {
	interface UsersItemListener {
		fun onClickedUser(userId: String)
	}
	private val items = ArrayList<UsersList>()
	fun setItems(items: ArrayList<UsersList>){
		this.items.clear()
		this.items.addAll(items)
		notifyDataSetChanged()
	}
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
		val binding: UserItemBinding = UserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
		return UsersViewHolder(binding, listener)
	}



	override fun getItemCount(): Int = items.size

	override fun onBindViewHolder(holder: UsersViewHolder, position: Int) = holder.bind(items[position])

}

class UsersViewHolder(
	private val itemBinding: UserItemBinding,
	private val listener: UserListAdapter.UsersItemListener,
) : RecyclerView.ViewHolder(itemBinding.root),
	View.OnClickListener {

	private lateinit var user: UsersList

	init {
		itemBinding.root.setOnClickListener(this)
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

	override fun onClick(v: View?) {
		listener.onClickedUser(user.id)
	}
}