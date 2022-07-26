package com.team23.home.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavDeepLinkRequest
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.team23.home.R
import androidx.navigation.fragment.findNavController
import com.team23.home.ui.adapters.PostListAdapter
import com.team23.home.ui.viewmodels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var postRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        postRecyclerView = requireView().findViewById(R.id.posts_list)
        initViews()
        initObservers()
    }

    private fun initViews() {
        postRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = PostListAdapter().apply {
                onItemClick = { userId -> navigateToUser(userId) }
            }
        }
    }

    private fun initObservers() {
        homeViewModel.isLoading.observe(viewLifecycleOwner) {
            requireView().findViewById<ProgressBar>(R.id.progress_home).visibility =
            if (it) {
                View.VISIBLE
            } else {
                View.GONE
            }
        }
        homeViewModel.posts.observe(viewLifecycleOwner) {
            (postRecyclerView.adapter as PostListAdapter).submitList(it)
        }
    }

    private fun navigateToUser(userId: String) {
        // TODO USE USERID
        val navController = findNavController()
        val request = NavDeepLinkRequest.Builder
            .fromUri("socialProfile://user".toUri())
            .build()
        navController.navigate(request)
    }
}