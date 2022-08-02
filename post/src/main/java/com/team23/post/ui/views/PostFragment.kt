package com.team23.post.ui.views

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.divider.MaterialDivider
import com.team23.core.extensions.handleVisibility
import com.team23.core.extensions.navigateToUser
import com.team23.core.extensions.toggle
import com.team23.post.R
import com.team23.post.ui.adapters.CommentListAdapter
import com.team23.post.ui.viewmodels.PostViewModel
import com.team23.post.ui.viewobjects.PostVO
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PostFragment : Fragment() {
    @Inject
    lateinit var viewModelAssistedFactory: PostViewModel.Factory
    private lateinit var postViewModel: PostViewModel
    private lateinit var commentRecyclerView: RecyclerView
    private lateinit var likesButton: LinearLayout
    private lateinit var likesAmount: TextView
    private lateinit var commentsButton: LinearLayout
    private lateinit var tagsButton: LinearLayout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val postViewModel: PostViewModel by viewModels {
            PostViewModel.provideFactory(
                viewModelAssistedFactory,
                arguments?.getString("postId")
            )
        }
        this.postViewModel = postViewModel

        return inflater.inflate(R.layout.fragment_post, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        commentRecyclerView = requireView().findViewById(R.id.comments_list)
        likesButton = requireView().findViewById(R.id.likes_tag)
        likesAmount = requireView().findViewById(R.id.item_likes_amount)
        commentsButton = requireView().findViewById(R.id.comments_tag)
        tagsButton = requireView().findViewById(R.id.tags_tag)
        initViews()
        initObservers()
    }

    private fun initViews() {
        requireView().findViewById<Toolbar>(R.id.post_toolbar).setNavigationOnClickListener {
            findNavController().popBackStack()
        }
        commentRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = CommentListAdapter().apply {
                onUserClick = { userId -> findNavController().navigateToUser(userId) }
            }
        }
        commentsButton.setOnClickListener { commentRecyclerView.toggle() }
        tagsButton.setOnClickListener { /* TODO */ }
    }

    @SuppressLint("SetTextI18n")
    private fun initObservers() {
        postViewModel.isLoading.observe(viewLifecycleOwner) {
            requireView().findViewById<ProgressBar>(R.id.progress_post).handleVisibility(it)
            requireView().findViewById<MaterialDivider>(R.id.post_picture_divider)
                .handleVisibility(!it)
            likesButton.handleVisibility(!it)
            commentsButton.handleVisibility(!it)
            tagsButton.handleVisibility(!it)
            requireView().findViewById<MaterialDivider>(R.id.post_comment_divider)
                .handleVisibility(!it)
        }
        postViewModel.post.observe(viewLifecycleOwner) { post ->
            requireView().findViewById<ImageView>(R.id.post_picture).setImageBitmap(post.postPicture)
            requireView().findViewById<ImageView>(R.id.user_picture).apply {
                this.setImageBitmap(post.userPicture)
                this.setOnClickListener { _ ->
                    findNavController().navigateToUser(post.userId)
                }
            }
            requireView().findViewById<TextView>(R.id.user_name).text = post.username
            requireView().findViewById<TextView>(R.id.post_date).text = post.postDate
            requireView().findViewById<TextView>(R.id.post_description).text = post.postDescription
            requireView().findViewById<TextView>(R.id.item_likes_amount).text =
                post.likesAmount.toString()
            requireView().findViewById<TextView>(R.id.item_tags_amount).text =
                post.tagsAmount.toString()
            likesButton.setOnClickListener { likePost(post) }
            likePost(post)
        }
        postViewModel.comments.observe(viewLifecycleOwner) {
            (commentRecyclerView.adapter as CommentListAdapter).submitList(it)

            requireView().findViewById<TextView>(R.id.item_comments_amount).text =
                it.size.toString()
        }
    }

    private fun likePost(postVO: PostVO) {
        var currentLikesAmount = likesAmount.text.toString().toInt()
        val isNotLiked = postVO.likesAmount == currentLikesAmount
        requireView().findViewById<ImageView>(R.id.item_likes_icon).handleVisibility(isNotLiked)
        requireView().findViewById<ImageView>(R.id.item_likes_icon_colored).handleVisibility(!isNotLiked)
        if (!isNotLiked) {
            currentLikesAmount ++
        } else {
            currentLikesAmount --
        }
        likesAmount.text = "$currentLikesAmount"
    }

}