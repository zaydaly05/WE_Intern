package com.example.formalpullrequest.di

import android.content.Context
import androidx.room.Room
import com.example.formalpullrequest.data.local.AppDatabase
import com.example.formalpullrequest.data.local.CustomerDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "we_customer_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideCustomerDao(
        database: AppDatabase
    ): CustomerDao {
        return database.customerDao()
    }
}