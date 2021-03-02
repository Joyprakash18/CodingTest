package com.example.codingtest.ui.userlist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.codingtest.R
import com.example.codingtest.databinding.FragmentUserListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_user_list.*

@AndroidEntryPoint
class UserListFragment : Fragment(R.layout.fragment_user_list) {
    private val viewModel: UserListViewModel by viewModels()
    private lateinit var binding: FragmentUserListBinding
    private lateinit var userListAdapter: UserListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentUserListBinding.bind(view)
        onClickListener()
        initializeRecyclerView()
        setObserver()
    }

    private fun setObserver() {
        viewModel.users.observe(viewLifecycleOwner) { list ->
            if (!list.isNullOrEmpty()) {
                if (::userListAdapter.isInitialized) {
                    userListAdapter.submitList(list)
                    binding.userListRecycler.visibility = View.VISIBLE
                    binding.placeHolder.visibility = View.GONE
                }
            } else {
                binding.userListRecycler.visibility = View.GONE
                binding.placeHolder.visibility = View.VISIBLE
            }
        }
    }

    private fun initializeRecyclerView() {
        userListAdapter = UserListAdapter()
        binding.apply {
            userListRecycler.apply {
                adapter = userListAdapter
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
            }
        }
    }

    private fun onClickListener() {
        binding.apply {
            backBtn.setOnClickListener {
                Navigation.findNavController(requireActivity(), R.id.navHostFragment).popBackStack()
            }
        }
    }
}