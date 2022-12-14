package com.example.kodetesttask.di

import android.app.Application
import androidx.room.Room
import com.example.kodetesttask.database.DatabaseApp
import com.example.kodetesttask.database.DatabaseDao
import com.example.kodetesttask.network.ApiService
import com.example.kodetesttask.network.NetworkUrl.Companion.BASE_URL
import com.example.kodetesttask.network.UsersRemoteDataSource
import com.example.kodetesttask.repository.MainRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module(includes = [ViewModelModule::class])
class AppModule {
	@Singleton
	@Provides
	fun provideDb(app: Application): DatabaseApp {
		return Room.databaseBuilder(app, DatabaseApp::class.java, "users_list_table")
			.fallbackToDestructiveMigration()
			.build()
	}

	@Singleton
	@Provides
	fun provideUserDao(db: DatabaseApp): DatabaseDao {
		return db.DatabaseDao()
	}

	@Singleton
	@Provides
	fun provideRetrofit(gson: Gson): Retrofit =
		Retrofit.Builder()
			.baseUrl(BASE_URL)
			.addConverterFactory(GsonConverterFactory.create(gson))
			.build()

	@Provides fun provideGson(): Gson = GsonBuilder().create()

	@Provides
	fun provideUsersService(retrofit: Retrofit): ApiService =
		retrofit.create(ApiService::class.java)

	@Singleton
	@Provides
	fun provideCharacterRemoteDataSource(apiService: ApiService) = UsersRemoteDataSource(apiService)

	@Singleton
	@Provides
	fun provideRepository(remoteDataSource: UsersRemoteDataSource, localDataSource: DatabaseDao) =
		MainRepository(remoteDataSource, localDataSource)
}
