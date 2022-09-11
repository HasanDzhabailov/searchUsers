package com.example.kodetesttask.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.get
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.kodetesttask.R
import com.example.kodetesttask.databinding.FragmentHomeBinding
import com.example.kodetesttask.di.Injectable
import com.example.kodetesttask.model.entity.UserItemEntity
import com.example.kodetesttask.utils.Resource
import com.example.kodetesttask.utils.autoCleared
import timber.log.Timber
import javax.inject.Inject


class HomeFragment : Fragment(), Injectable, HomeAdapter.UsersItemListener {
	@Inject
	lateinit var viewModelFactory: ViewModelProvider.Factory

	private var binding by autoCleared<FragmentHomeBinding>()

	lateinit var homeViewModel: HomeViewModel
	lateinit var adapter: HomeAdapter

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
		setupRecyclerView()
		setupObservers()
		setupOnClickTab()



	}
		private fun getUserWithFilters(department: String){
			homeViewModel.getUsersFilter(department)
				.observe(viewLifecycleOwner){dep->
					adapter.setItems(ArrayList(dep))
				}
		}
		private fun setupObservers(){
			homeViewModel.userList.observe(viewLifecycleOwner, Observer {
				Log.d("@@@test", it.status.toString())
				when (it.status) {

					Resource.Status.SUCCESS -> {
						binding.loading.visibility = View.GONE
						if (!it.data.isNullOrEmpty()) adapter.setItems(ArrayList(it.data))
					}
					Resource.Status.ERROR ->
						Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()

					Resource.Status.LOADING ->
						binding.loading.visibility = View.VISIBLE
				}
			})
		}
	private fun setupRecyclerView() {
		adapter = HomeAdapter(this)
		binding.userListRv.layoutManager = LinearLayoutManager(requireContext())
		binding.userListRv.adapter = adapter

	}
	private fun setupOnClickTab(){

	}

	override fun onClickedUser(userId: String) {

		findNavController().navigate(

			R.id.action_homeFragment_to_profileDetailFragment,
			bundleOf("id" to userId)
		)
	}
}