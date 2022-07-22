package com.team23.home.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.team23.home.R
import com.team23.home.ui.adapters.PostListAdapter
import com.team23.home.ui.viewmodels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment: Fragment() {
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
        postRecyclerView = requireView().findViewById(R.id.fragment_home)
        initViews()
        initObservers()
    }

    private fun initViews() {
        postRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = PostListAdapter()
        }
    }

    private fun initObservers() {
        homeViewModel.isLoading.observe(viewLifecycleOwner) {
            // TODO
        }
        homeViewModel.posts.observe(viewLifecycleOwner) {
            (postRecyclerView.adapter as PostListAdapter).submitList(it)
        }
    }
}