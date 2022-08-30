package com.example.kodetesttask.di

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import com.example.kodetesttask.database.DatabaseApp
import com.example.kodetesttask.database.DatabaseDao

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class AppModule {
	@Singleton
	@Provides
	fun provideDb(app: Application): DatabaseApp {
		return Room
			.databaseBuilder(app, DatabaseApp::class.java,"name_table")
			.fallbackToDestructiveMigration()
			.build()
	}
	@Singleton
	@Provides
	fun provideUserDao(db: DatabaseApp): DatabaseDao {
		return db.DatabaseDao()
	}
}