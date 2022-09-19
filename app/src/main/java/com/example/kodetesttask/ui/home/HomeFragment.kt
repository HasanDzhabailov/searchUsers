package com.example.kodetesttask.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.kodetesttask.Consts.tabName
import com.example.kodetesttask.Consts.tabValue

import com.example.kodetesttask.R
import com.example.kodetesttask.databinding.FragmentHomeBinding
import com.example.kodetesttask.di.Injectable
import com.example.kodetesttask.ui.users.UserListAdapter
import com.example.kodetesttask.ui.users.UsersListFragment
import com.example.kodetesttask.utils.autoCleared
import com.google.android.material.tabs.TabLayoutMediator
import javax.inject.Inject


class HomeFragment : Fragment(), Injectable {
	@Inject
	lateinit var viewModelFactory: ViewModelProvider.Factory

	private lateinit var adapter:HomePager2Adapter
	private var binding by autoCleared<FragmentHomeBinding>()

	lateinit var homeViewModel: HomeViewModel

	private lateinit var viewPager: ViewPager2
	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?,
	): View? {
		binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
		homeViewModel =
			ViewModelProvider(this, viewModelFactory)[HomeViewModel::class.java]
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		binding.homeViewModel = homeViewModel
		binding.lifecycleOwner = this
		adapter = HomePager2Adapter(this)
		viewPager = binding.viewPager2


	viewPager.adapter = adapter
		TabLayoutMediator(binding.tabLayout, viewPager){tab, position->
			tab.text = "${tabValue[position]}"
		}.attach()

		setupOnClickTab()


	}

//		private fun setupObservers(){
//			homeViewModel.userList.observe(viewLifecycleOwner, Observer {
//				Log.d("@@@test", it.status.toString())
//				when (it.status) {
//
//					Resource.Status.SUCCESS -> {
//						binding.loading.visibility = View.GONE
//						if (!it.data.isNullOrEmpty()) adapter.setItems(ArrayList(it.data))
//					}
//					Resource.Status.ERROR ->
//						Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
//
//					Resource.Status.LOADING ->
//						binding.loading.visibility = View.VISIBLE
//				}
//			})
//		}
//	private fun setupRecyclerView() {
//		adapter = HomeAdapter(this)
//		binding.userListRv.layoutManager = LinearLayoutManager(requireContext())
//		binding.userListRv.adapter = adapter
//
//	}
	private fun setupOnClickTab(){

	}


}