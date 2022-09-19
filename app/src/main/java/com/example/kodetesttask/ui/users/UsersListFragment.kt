package com.example.kodetesttask.ui.users

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kodetesttask.Consts.ARG_TAG
import com.example.kodetesttask.R
import com.example.kodetesttask.databinding.FragmentUsersBinding
import com.example.kodetesttask.di.Injectable
import com.example.kodetesttask.utils.Resource
import com.example.kodetesttask.utils.autoCleared
import javax.inject.Inject


open class UsersListFragment : Fragment(), Injectable, UserListAdapter.UsersItemListener {
	@Inject
	lateinit var viewModelFactory: ViewModelProvider.Factory

	lateinit var usersListViewModel: UsersListViewModel
	lateinit var adapter: UserListAdapter

	private var binding by autoCleared<FragmentUsersBinding>()
	private var department: String? = null


	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?,
	): View? {
		binding = DataBindingUtil.inflate(inflater, R.layout.fragment_users, container, false)
		usersListViewModel =
			ViewModelProvider(this, viewModelFactory)[UsersListViewModel::class.java]
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		arguments?.takeIf {
			it.containsKey(ARG_TAG)?.apply {
				department = requireArguments().getString(ARG_TAG)
			}!!
		}
		binding.usersListViewModel = usersListViewModel
		binding.lifecycleOwner = this
		setupObservers()
		setupRecyclerView()
		filterTabsSetup()
	}

	override fun onClickedUser(userId: String) {
		findNavController().navigate(
			R.id.action_homeFragment_to_profileDetailFragment,
			bundleOf("id" to userId)
		)
	}

	private fun setupRecyclerView() {
		adapter = UserListAdapter(this)
		binding.userListRv.layoutManager = LinearLayoutManager(requireContext())
		binding.userListRv.adapter = adapter
	}

	private fun setupObservers() {
		usersListViewModel.userList.observe(viewLifecycleOwner, Observer {
			when (it.status) {

				Resource.Status.SUCCESS -> {
					binding.loading.visibility = View.GONE
					var filter = getCategory()
					if (!it.data.isNullOrEmpty()&& filter == "all"){

						adapter.setItems(ArrayList(it.data))
					}

				}
				Resource.Status.ERROR ->
					Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()

				Resource.Status.LOADING ->
					binding.loading.visibility = View.VISIBLE
			}
		})
	}
	open fun getCategory(): String{
		return "all"
	}

	private fun filterTabsSetup(){
		usersListViewModel.getFilterUsersList(getCategory())
			.observe(viewLifecycleOwner){ users ->
				adapter.setItems(ArrayList(users))

			}
	}
}