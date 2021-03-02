package com.example.codingtest.data.repository

import com.example.codingtest.data.localdb.UserDao
import com.example.codingtest.data.model.UserModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(private val userDao: UserDao) {

    fun userList(): Flow<List<UserModel>> {
        return userDao.getAllUser()
    }

    suspend fun addUser(userModel: UserModel): Long {
        return withContext(Dispatchers.IO) {
            userDao.addUser(userModel)
        }
    }
}