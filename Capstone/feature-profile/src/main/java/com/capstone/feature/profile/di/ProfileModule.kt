package com.capstone.feature.profile.di

import com.capstone.feature.profile.data.ProfileApi
import com.capstone.feature.profile.data.ProfileRepositoryImpl
import com.capstone.feature.profile.domain.ProfileRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProfileModule {

    @Provides
    @Singleton
    fun provideProfileApi(retrofit: Retrofit): ProfileApi =
        retrofit.create(ProfileApi::class.java)

    @Provides
    @Singleton
    fun provideProfileRepository(api: ProfileApi): ProfileRepository =
        ProfileRepositoryImpl(api)
}
