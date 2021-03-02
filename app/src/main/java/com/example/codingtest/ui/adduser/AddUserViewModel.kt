package com.example.codingtest.ui.adduser

import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.codingtest.data.model.UserModel
import com.example.codingtest.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class AddUserViewModel @Inject constructor(private val userRepository: UserRepository) :
    ViewModel() {
    private var hasError: Boolean = false
    private var nameErrorMessage: String? = null
    private var addressErrorMessage: String? = null
    private var phoneErrorMessage: String? = null
    private var emailErrorMessage: String? = null

    fun addUser(name: String, address: String, phone: String, email: String) =
        liveData(Dispatchers.IO) {
            checkDataValidation(name, address, phone, email)
            if (hasError) {
                emit(
                    State.DataValidationError(
                        nameErrorMessage, addressErrorMessage, phoneErrorMessage, emailErrorMessage
                    )
                )
            } else {
                val userModel = UserModel(
                    name = name,
                    address = address,
                    phone = phone,
                    email = email
                )
                val response = userRepository.addUser(userModel)
                if (response >= 1) {
                    emit(State.Success("Saved Successfully"))
                } else {
                    emit(State.Error("OOPS...Something went wrong"))
                }
            }
        }

    private fun checkDataValidation(
        name: String,
        address: String,
        phone: String,
        email: String
    ) {
        hasError = false
        validateName(name)
        validateAddress(address)
        validatePhone(phone)
        validateEmail(email)
    }

    private fun validateName(name: String) {
        if (name.isEmpty()) {
            hasError = true
            nameErrorMessage = "Please enter name"
        } else {
            nameErrorMessage = null
        }
    }

    private fun validateAddress(address: String) {
        if (address.isEmpty()) {
            hasError = true
            addressErrorMessage = "Please enter address"
        } else {
            addressErrorMessage = null
        }
    }

    private fun validatePhone(phone: String) {
        if (phone.isEmpty()) {
            hasError = true
            phoneErrorMessage = "Please enter phone number"
        } else if (!Patterns.PHONE.matcher(phone).matches()) {
            hasError = true
            phoneErrorMessage = "Please enter valid phone number"
        } else {
            phoneErrorMessage = null
        }
    }

    private fun validateEmail(email: String) {
        if (email.isEmpty()) {
            hasError = true
            emailErrorMessage = "Please enter email id"
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            hasError = true
            emailErrorMessage = "Please enter valid email id"
        } else {
            emailErrorMessage = null
        }
    }

    sealed class State {
        data class Success(val message: String?) : State()
        data class Error(val message: String?) : State()
        data class DataValidationError(
            val nameErrorMessage: String?,
            val addressErrorMessage: String?,
            val phoneErrorMessage: String?,
            val emailErrorMessage: String?
        ) : State()
    }
}