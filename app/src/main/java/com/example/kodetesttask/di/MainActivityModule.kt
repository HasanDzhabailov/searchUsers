package com.example.kodetesttask.di


import com.example.kodetesttask.MainActivity
import com.example.kodetesttask.di.FragmentBuildersModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class MainActivityModule {
	@ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
	abstract fun contributeMainActivity(): MainActivity
}