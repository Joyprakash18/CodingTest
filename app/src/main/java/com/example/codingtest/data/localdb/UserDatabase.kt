package com.example.codingtest.data.localdb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.codingtest.data.model.UserModel

@Database(entities = [UserModel::class], version = 1)
abstract class UserDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
}