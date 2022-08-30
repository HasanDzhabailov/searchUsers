package com.example.kodetesttask

import android.app.Application

import com.example.kodetesttask.di.AppInjector
import dagger.android.AndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject
import dagger.android.DispatchingAndroidInjector
import timber.log.Timber

class App: Application(), HasAndroidInjector {
	@Inject
	lateinit var androidInjector: DispatchingAndroidInjector<Any>
	override fun onCreate() {
		super.onCreate()
		if (BuildConfig.DEBUG) {
			Timber.plant(Timber.DebugTree())
		}
		AppInjector.init(this)
	}

	override fun androidInjector(): AndroidInjector<Any> = androidInjector
}