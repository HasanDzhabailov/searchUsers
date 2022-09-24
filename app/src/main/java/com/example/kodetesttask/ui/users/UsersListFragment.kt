package com.example.kodetesttask.ui.users

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.NotificationCompat.getCategory
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kodetesttask.Consts.ARG_TAG
import com.example.kodetesttask.R
import com.example.kodetesttask.databinding.FragmentUsersBinding
import com.example.kodetesttask.di.Injectable
import com.example.kodetesttask.model.Users
import com.example.kodetesttask.ui.error.ErrorFragment
import com.example.kodetesttask.ui.home.HomeFragment
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
	private var recyclerViewAdapter: UserListAdapter? = null

	private fun setupObservers() {

		usersListViewModel.getUserListNetwork(checkSortType())
			.observe(viewLifecycleOwner, Observer {
				when (it.status) {
					Resource.Status.SUCCESS -> {
						binding.loading.visibility = View.GONE
						var filter = getCategory()
						if (!it.data.isNullOrEmpty() && filter == "all") {
							adapter.setItems(ArrayList(it.data))
						}
					}
					Resource.Status.ERROR -> {
						findNavController().navigate(
							R.id.action_homeFragment_to_errorFragment
						)
					}

					Resource.Status.LOADING ->
						binding.loading.visibility = View.VISIBLE
				}
			})
	}

	private fun setupRecyclerView() {
		adapter = getAdapter()
		binding.userListRv.layoutManager = LinearLayoutManager(requireContext())
		binding.userListRv.adapter = adapter
	}

	private fun filterTabsSetup() {
		if (getCategory() == "all") {
			getAllUsers(checkSortType())
		}
		usersListViewModel.getFilterUsersList(getCategory(), checkSortType())
			.observe(viewLifecycleOwner) { users ->
				if (!users.isNullOrEmpty())
					adapter.setItems(ArrayList(users))
			}
	}

	private fun getAdapter(users: Users = Users()): UserListAdapter {
		if (recyclerViewAdapter == null)
			recyclerViewAdapter = UserListAdapter(
				this,
				requireContext(),
				users,
				(parentFragment as HomeFragment).getSortType()
			)
		return recyclerViewAdapter!!
	}

	private fun getAllUsers(sort: Int) {
		usersListViewModel.getAllUsersList(sort).observe(viewLifecycleOwner) { users ->
			if (!users.isNullOrEmpty()) {
				adapter.setItems(ArrayList(users))
			}
		}
	}

	/**
	 * checkSortType
	 * return 2 - order by birthday
	 * return 1 - order by first name
	 */
	private fun checkSortType(): Int {
		if (getAdapter().sortType == SortType.DATE) return 2
		return 1
	}

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
		binding.lifecycleOwner = viewLifecycleOwner
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

	open fun getCategory(): String {
		return "all"
	}

	fun search(str: String) {
		getAdapter().search = str
	}

	fun getSort(): SortType {
		return getAdapter().sortType
	}

	fun updateSort() {
		getAdapter().sortType = (parentFragment as HomeFragment).getSortType()
		filterTabsSetup()
	}

	enum class SortType {
		ALPHABET,
		DATE
	}

}
