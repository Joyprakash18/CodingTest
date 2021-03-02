package com.example.codingtest.ui.userlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.codingtest.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(private val userRepository: UserRepository): ViewModel() {
    val users = userRepository.userList().asLiveData()
}