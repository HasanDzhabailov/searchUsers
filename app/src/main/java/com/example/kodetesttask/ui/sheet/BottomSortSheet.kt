package com.example.kodetesttask.ui.sheet

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kodetesttask.R
import com.example.kodetesttask.databinding.DialogSortBinding
import com.example.kodetesttask.ui.home.HomeFragment
import com.example.kodetesttask.ui.users.UsersListFragment
import com.example.kodetesttask.utils.autoCleared
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSortSheet: BottomSheetDialogFragment() {

	private  var binding by autoCleared<DialogSortBinding>()

	override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
		return BottomSheetDialog(requireContext(), R.style.ThemeOverlay_App_BottomSheetDialog)
	}

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		binding = DialogSortBinding.bind(inflater.inflate(R.layout.dialog_sort, container, false))
		dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		binding.root.setBackgroundColor(Color.TRANSPARENT)
		checkSorts()
		binding.radioButton.setOnClickListener { (parentFragment as HomeFragment).setSortType(UsersListFragment.SortType.ALPHABET) }
		binding.radioButton2.setOnClickListener { (parentFragment as HomeFragment).setSortType(UsersListFragment.SortType.DATE) }
	}

	private fun checkSorts(){
		if((parentFragment as HomeFragment).getCurrentViewPagerItemFragment()?.getSort() == UsersListFragment.SortType.ALPHABET)
			binding.radioButton.isChecked = true
		else
			binding.radioButton2.isChecked = true
	}
}