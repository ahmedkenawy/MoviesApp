package com.ahmedkenawy.moviesapp.modules.local

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.ahmedkenawy.moviesapp.features.list.data.local.MoviesDatabase
import com.ahmedkenawy.moviesapp.utils.Constants.Intent.DATABASE_NAME
import com.ahmedkenawy.moviesapp.utils.Constants.Intent.SHARED_PREFERENCES_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): MoviesDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            MoviesDatabase::class.java,
            DATABASE_NAME
        ).build()
    }

    @Singleton
    @Provides
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
    }
}