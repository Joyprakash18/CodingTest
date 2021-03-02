package com.example.codingtest.di

import android.app.Application
import androidx.room.Room
import com.example.codingtest.data.localdb.UserDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideDatabase(app: Application) =
        Room.databaseBuilder(app, UserDatabase::class.java, "Users.db")
            .build()

    @Provides
    @Singleton
    fun provideUserDao(db: UserDatabase) = db.userDao()
}