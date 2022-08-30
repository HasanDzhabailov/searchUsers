package com.example.kodetesttask.ui.profiledetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.kodetesttask.R
import com.example.kodetesttask.databinding.FragmentHomeBinding
import com.example.kodetesttask.databinding.FragmentProfileDetailBinding
import com.example.kodetesttask.di.Injectable
import com.example.kodetesttask.ui.home.HomeViewModel
import com.example.kodetesttask.utils.autoCleared
import javax.inject.Inject


class ProfileDetailFragment : Fragment(), Injectable {
	@Inject
	lateinit var viewModelFactory: ViewModelProvider.Factory
	private var binding by autoCleared<FragmentProfileDetailBinding>()
	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?,
	): View? {
		binding = DataBindingUtil.inflate(inflater,R.layout.fragment_profile_detail,container,false)
		return binding.root
	}
	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		val profileDetailViewModel =
			ViewModelProvider(this, viewModelFactory)[ProfileDetailViewModel::class.java]
		binding.profileDetailViewModel = profileDetailViewModel
		binding.lifecycleOwner = this
	}

}