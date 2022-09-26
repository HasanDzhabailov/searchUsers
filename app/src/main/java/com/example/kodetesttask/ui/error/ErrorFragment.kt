package com.example.kodetesttask.ui.error

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.kodetesttask.R
import com.example.kodetesttask.databinding.FragmentFailureBinding
import com.example.kodetesttask.di.Injectable
import com.example.kodetesttask.utils.autoCleared
import javax.inject.Inject

class ErrorFragment : Fragment(), Injectable {
	@Inject
	lateinit var viewModelFactory: ViewModelProvider.Factory

	lateinit var errorViewModel: ErrorViewModel
	private var binding by autoCleared<FragmentFailureBinding>()

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?,
	): View {

		binding =
			FragmentFailureBinding.bind(
				inflater.inflate(R.layout.fragment_failure, container, false)
			)
		errorViewModel = ViewModelProvider(this, viewModelFactory)[ErrorViewModel::class.java]
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		binding.errorViewModel = errorViewModel
		binding.lifecycleOwner = viewLifecycleOwner
		(activity as AppCompatActivity?)!!.supportActionBar!!.hide()
		binding.textView3.setOnClickListener {
			errorViewModel.clearDataFromDatabase()
			findNavController().navigate(R.id.action_errorFragment_to_homeFragment)
		}

	}

	override fun onResume() {
		super.onResume()
		//errorViewModel?.clearDataFromDatabase()
	}
}
