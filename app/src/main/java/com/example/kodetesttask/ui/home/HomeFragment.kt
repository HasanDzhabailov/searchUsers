package com.example.kodetesttask.ui.home

import android.graphics.Color
import android.os.Bundle

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener

import androidx.databinding.DataBindingUtil

import androidx.lifecycle.ViewModelProvider

import androidx.viewpager2.widget.ViewPager2
import com.example.kodetesttask.Consts.tabValue


import com.example.kodetesttask.R
import com.example.kodetesttask.databinding.FragmentHomeBinding
import com.example.kodetesttask.di.Injectable
import com.example.kodetesttask.model.UsersList
import com.example.kodetesttask.ui.sheet.BottomSortSheet

import com.example.kodetesttask.ui.users.UsersListFragment
import com.example.kodetesttask.utils.autoCleared
import com.google.android.material.tabs.TabLayoutMediator

import javax.inject.Inject
import kotlin.collections.ArrayList


class HomeFragment : Fragment(), Injectable {
	@Inject
	lateinit var viewModelFactory: ViewModelProvider.Factory

	lateinit var homeViewModel: HomeViewModel
	lateinit var usersList:ArrayList<UsersList>

	private lateinit var viewPager: ViewPager2
	private lateinit var adapter: HomePager2Adapter

	private var binding by autoCleared<FragmentHomeBinding>()
	private var sortType: UsersListFragment.SortType = UsersListFragment.SortType.ALPHABET

	private fun search(str: String){
		if(str != "")
			binding.imageButton.setColorFilter(Color.parseColor("#050510"))
		else
			binding.imageButton.colorFilter = null

		(binding.viewPager2.adapter as HomePager2Adapter).getFragment(binding.viewPager2.currentItem)?.let {
			it.search(str)
		}
	}

	private fun getMainViewPagerAdapter(): HomePager2Adapter{
		return HomePager2Adapter(
			childFragmentManager,
			lifecycle
		)
	}

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
		binding.lifecycleOwner = viewLifecycleOwner
		adapter = getMainViewPagerAdapter()
		viewPager = binding.viewPager2
		viewPager.adapter = adapter
		TabLayoutMediator(binding.tabLayout, viewPager) { tab, position ->
			tab.text = "${tabValue[position]}"
		}.attach()

		binding.editTextTextPersonName2.addTextChangedListener {
			search(it.toString())
		}
		binding.imageButtonSort.setOnClickListener(){
			val dialog = BottomSortSheet()
			dialog.show(childFragmentManager, "SORT")
		}
	}


	fun getSortType(): UsersListFragment.SortType{
		return sortType
	}

	fun setSortType(sortType: UsersListFragment.SortType){
		this.sortType = sortType

		if(sortType == UsersListFragment.SortType.DATE)
			binding.imageButtonSort.setColorFilter(Color.parseColor("#6534FF"))
		else
			binding.imageButtonSort.colorFilter = null
	getCurrentViewPagerItemFragment()?.updateSort()

	}

	fun getCurrentViewPagerItemFragment(): UsersListFragment?{
		return (binding.viewPager2.adapter as HomePager2Adapter).getFragment(binding.viewPager2.currentItem)
	}
}