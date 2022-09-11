package com.example.kodetesttask.ui.profiledetail

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.example.kodetesttask.R
import com.example.kodetesttask.databinding.FragmentHomeBinding
import com.example.kodetesttask.databinding.FragmentProfileDetailBinding
import com.example.kodetesttask.di.Injectable
import com.example.kodetesttask.model.UsersList
import com.example.kodetesttask.ui.home.HomeFragmentDirections
import com.example.kodetesttask.ui.home.HomeViewModel
import com.example.kodetesttask.utils.autoCleared
import com.example.kodetesttask.utils.convertDateToLong
import com.example.kodetesttask.utils.convertLongToTime
import java.time.LocalDateTime
import javax.inject.Inject


class ProfileDetailFragment : Fragment(), Injectable {
	@Inject
	lateinit var viewModelFactory: ViewModelProvider.Factory
	private var binding by autoCleared<FragmentProfileDetailBinding>()
	lateinit var profileDetailViewModel:ProfileDetailViewModel

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?,
	): View? {
		binding = DataBindingUtil.inflate(inflater,R.layout.fragment_profile_detail,container,false)
		profileDetailViewModel =
			ViewModelProvider(this, viewModelFactory)[ProfileDetailViewModel::class.java]
		return binding.root
	}
	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		binding.profileDetailViewModel = profileDetailViewModel
		binding.lifecycleOwner = this
		arguments?.getString("id")?.let { profileDetailViewModel.start(it) }
		setupObserver()
	}

private fun setupObserver(){
	profileDetailViewModel.character.observe(viewLifecycleOwner, Observer {
		bindUser(it)
	})
//
}
	private fun bindUser(user:UsersList){

		binding.fullNameProfileTextView.text = "${user.firstName}  ${user.lastName}"
		binding.userTagProfileTextView.text = user.userTag
		binding.ageProfileTextView.text = ((System.currentTimeMillis()-convertDateToLong(user.birthday))/ 31556952000 ).toString() + " лет"
		binding.birthdayProfileTextView.text = convertLongToTime(convertDateToLong(user.birthday))


		binding.phoneProfileTextView.text = user.phone
		Glide.with(binding.root)
			.load(user.avatarUrl)
			.transform(CircleCrop())
			.into(binding.avatarProfileImageView)
	}
}