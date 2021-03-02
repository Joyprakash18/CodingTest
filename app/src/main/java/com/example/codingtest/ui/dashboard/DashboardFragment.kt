package com.example.codingtest.ui.dashboard

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.codingtest.R
import com.example.codingtest.databinding.FragmentDashboardBinding
import dagger.hilt.android.AndroidEntryPoint

class DashboardFragment : Fragment(R.layout.fragment_dashboard) {
    private lateinit var binding: FragmentDashboardBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDashboardBinding.bind(view)
        onClickListener()
    }

    private fun onClickListener() {
        binding.apply {
            addUserBtn.setOnClickListener {
                Navigation.findNavController(requireActivity(), R.id.navHostFragment)
                    .navigate(R.id.action_dashboardFragment_to_addUserFragment)
            }
            userListBtn.setOnClickListener {
                Navigation.findNavController(requireActivity(), R.id.navHostFragment)
                    .navigate(R.id.action_dashboardFragment_to_userListFragment)
            }
        }
    }
}