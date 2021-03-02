package com.example.codingtest.ui.adduser

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.codingtest.R
import com.example.codingtest.databinding.FragmentAddUserBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AddUserFragment : Fragment(R.layout.fragment_add_user) {
    private val viewModel: AddUserViewModel by viewModels()
    private lateinit var binding: FragmentAddUserBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAddUserBinding.bind(view)
        onClickListener()
    }

    private fun onClickListener() {
        binding.backBtn.setOnClickListener {
            Navigation.findNavController(requireActivity(), R.id.navHostFragment).popBackStack()
        }
        binding.apply {
            backBtn.setOnClickListener {
                Navigation.findNavController(requireActivity(), R.id.navHostFragment).popBackStack()
            }
            saveBtn.setOnClickListener {
                val name = name.text.toString()
                val address = address.text.toString()
                val phone = phone.text.toString()
                val email = email.text.toString()
                viewModel.addUser(
                    name, address, phone, email
                ).observe(viewLifecycleOwner) { state ->
                    when (state) {
                        is AddUserViewModel.State.Success -> {
                            Toast.makeText(requireActivity(), state.message, Toast.LENGTH_SHORT)
                                .show()
                            Navigation.findNavController(requireActivity(), R.id.navHostFragment)
                                .popBackStack()
                        }
                        is AddUserViewModel.State.Error -> {
                            Toast.makeText(requireActivity(), state.message, Toast.LENGTH_SHORT)
                                .show()
                        }
                        is AddUserViewModel.State.DataValidationError -> {
                            nameLayout.error = state.nameErrorMessage
                            addressLayout.error = state.addressErrorMessage
                            phoneLayout.error = state.phoneErrorMessage
                            emailLayout.error = state.emailErrorMessage
                        }
                    }
                }
            }
        }
    }
}