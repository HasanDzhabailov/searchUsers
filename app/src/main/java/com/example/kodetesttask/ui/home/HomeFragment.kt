package com.example.kodetesttask.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import com.example.kodetesttask.R
import com.example.kodetesttask.databinding.FragmentHomeBinding
import com.example.kodetesttask.di.Injectable
import com.example.kodetesttask.model.entity.UserItemEntity
import com.example.kodetesttask.utils.autoCleared
import timber.log.Timber
import javax.inject.Inject


class HomeFragment : Fragment(), Injectable {
	@Inject
	lateinit var viewModelFactory: ViewModelProvider.Factory

	private var binding by autoCleared<FragmentHomeBinding>()

	lateinit var homeViewModel: HomeViewModel

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?,
	): View? {
		binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
	

		homeViewModel =
			ViewModelProvider(this, viewModelFactory)[HomeViewModel::class.java]
		binding.homeViewModel = homeViewModel
		binding.lifecycleOwner = this
		setupObservers()

	}
		private fun setupObservers(){
			homeViewModel.userList.observe(viewLifecycleOwner, Observer {
				Log.d("@@@pidar", it.toString())
				binding.textView.text = it.toString()
			})
		}
}