package com.example.kodetesttask.ui.profiledetail


import android.Manifest.permission.CALL_PHONE
import android.content.Intent
import android.content.pm.PackageManager

import android.net.Uri
import android.os.Bundle

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

import androidx.databinding.DataBindingUtil

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.example.kodetesttask.R

import com.example.kodetesttask.databinding.FragmentProfileDetailBinding
import com.example.kodetesttask.di.Injectable
import com.example.kodetesttask.model.UsersList

import com.example.kodetesttask.utils.autoCleared
import com.example.kodetesttask.utils.convertDateToLong
import com.example.kodetesttask.utils.convertLongToTime

import javax.inject.Inject


class ProfileDetailFragment : Fragment(), Injectable {
	@Inject
	lateinit var viewModelFactory: ViewModelProvider.Factory
	lateinit var profileDetailViewModel: ProfileDetailViewModel

	private var binding by autoCleared<FragmentProfileDetailBinding>()

	private fun setupObserver() {
		profileDetailViewModel.character.observe(viewLifecycleOwner, Observer {
			bindUser(it)
		})

	}

	private fun call(phone: String) {
		var permissionCallStatus = ContextCompat.checkSelfPermission(requireActivity(), CALL_PHONE)
		val callIntent = Intent(Intent.ACTION_CALL)
		callIntent.data = Uri.parse("tel:$phone")
		if (permissionCallStatus == PackageManager.PERMISSION_GRANTED) {
			startActivity(callIntent)
		} else {
			ActivityCompat.requestPermissions(requireActivity(),
				arrayOf(CALL_PHONE),
				1)
		}
	}

	private fun bindUser(user: UsersList) {
		binding.fullNameProfileTextView.text = "${user.firstName}  ${user.lastName}"
		binding.userTagProfileTextView.text = user.userTag

		binding.ageProfileTextView.text =
			((System.currentTimeMillis() - convertDateToLong(user.birthday)) / 31556952000).toString() + " лет"

		binding.birthdayProfileTextView.text = convertLongToTime(convertDateToLong(user.birthday))
		binding.phoneProfileTextView.text = user.phone

		binding.phoneProfileTextView.setOnClickListener {
			call(user.phone)
		}

		Glide.with(binding.root)
			.load(user.avatarUrl)
			.transform(CircleCrop())
			.into(binding.avatarProfileImageView)
	}


	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?,
	): View? {
		binding =
			DataBindingUtil.inflate(inflater, R.layout.fragment_profile_detail, container, false)
		profileDetailViewModel =
			ViewModelProvider(this, viewModelFactory)[ProfileDetailViewModel::class.java]
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		binding.profileDetailViewModel = profileDetailViewModel
		binding.lifecycleOwner = viewLifecycleOwner

		arguments?.getString("id")?.let { profileDetailViewModel.start(it) }
		setupObserver()
	}

}