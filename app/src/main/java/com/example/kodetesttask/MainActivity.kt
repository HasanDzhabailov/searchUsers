package com.example.kodetesttask


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasAndroidInjector {
	@Inject
	lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		val navController = findNavController(R.id.nav_host_fragment)
		val appBarConfiguration = AppBarConfiguration(setOf(
			R.id.homeFragment,
			R.id.profileDetailFragment,
		))
		setupActionBarWithNavController(navController, appBarConfiguration)
	}
	override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector
}