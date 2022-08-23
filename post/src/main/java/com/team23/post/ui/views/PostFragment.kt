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
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.android.material.divider.MaterialDivider
import com.team23.core.extensions.handleVisibility
import com.team23.core.extensions.navigateToUser
import com.team23.core.extensions.toggle
import com.team23.post.R
import com.team23.post.ui.adapters.CommentListAdapter
import com.team23.post.ui.adapters.TagsListAdapter
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
    private lateinit var tagsRecyclerView: RecyclerView

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
        tagsRecyclerView = requireView().findViewById(R.id.tags_list)
        initViews()
        initObservers()
    }

    private fun initViews() {
        requireView().findViewById<Toolbar>(R.id.post_toolbar).setNavigationOnClickListener {
            findNavController().popBackStack()
        }
        commentRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = CommentListAdapter(this@PostFragment.requireContext()).apply {
                onUserClick = { userId -> findNavController().navigateToUser(userId) }
            }
        }
        tagsRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, true)
            adapter = TagsListAdapter()
        }
        requireView().findViewById<LinearLayout>(R.id.comments_tag).setOnClickListener {
            requireView().findViewById<ImageView>(R.id.item_comments_icon).toggle()
            requireView().findViewById<ImageView>(R.id.item_comments_icon_colored).toggle()
            commentRecyclerView.toggle()
        }
        requireView().findViewById<LinearLayout>(R.id.tags_tag).setOnClickListener {
            requireView().findViewById<ImageView>(R.id.item_tags_icon).toggle()
            requireView().findViewById<ImageView>(R.id.item_tags_icon_colored).toggle()
            tagsRecyclerView.toggle()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun initObservers() {
        postViewModel.isLoading.observe(viewLifecycleOwner) {
            requireView().findViewById<ProgressBar>(R.id.progress_post).handleVisibility(it)
            requireView().findViewById<MaterialDivider>(R.id.post_picture_divider)
                .handleVisibility(!it)
            requireView().findViewById<LinearLayout>(R.id.likes_tag).handleVisibility(!it)
            requireView().findViewById<LinearLayout>(R.id.comments_tag).handleVisibility(!it)
            requireView().findViewById<LinearLayout>(R.id.tags_tag).handleVisibility(!it)
            requireView().findViewById<MaterialDivider>(R.id.post_comment_divider)
                .handleVisibility(!it)
        }
        postViewModel.post.observe(viewLifecycleOwner) { post ->
            Glide.with(requireContext())
                .load(post.postPicture)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .into(requireView().findViewById(R.id.post_picture))
            requireView().findViewById<ImageView>(R.id.user_picture).let {
                Glide.with(requireContext())
                    .load(post.userPicture)
                    .circleCrop()
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .into(it)
                it.setOnClickListener {
                    findNavController().navigateToUser(post.userId)
                }
            }
            requireView().findViewById<TextView>(R.id.user_name).text = post.username
            requireView().findViewById<TextView>(R.id.post_date).text = post.postDate
            requireView().findViewById<TextView>(R.id.post_description).text = post.postDescription
            requireView().findViewById<TextView>(R.id.item_likes_amount).text =
                post.likesAmount.toString()
            requireView().findViewById<TextView>(R.id.item_tags_amount).text =
                post.tags.size.toString()
            requireView().findViewById<LinearLayout>(R.id.likes_tag).setOnClickListener { likePost(post) }
            likePost(post)
            (tagsRecyclerView.adapter as TagsListAdapter).submitList(post.tags)
        }
        postViewModel.comments.observe(viewLifecycleOwner) {
            (commentRecyclerView.adapter as CommentListAdapter).submitList(it)

            requireView().findViewById<TextView>(R.id.item_comments_amount).text =
                it.size.toString()
        }
    }

    /**
     * Method for UI purpose only should be
     */
    private fun likePost(postVO: PostVO) {
        val itemLikes = requireView().findViewById<TextView>(R.id.item_likes_amount)
        var currentLikesAmount = itemLikes.text.toString().toInt()
        val isNotLiked = postVO.likesAmount == currentLikesAmount
        requireView().findViewById<ImageView>(R.id.item_likes_icon).handleVisibility(isNotLiked)
        requireView().findViewById<ImageView>(R.id.item_likes_icon_colored)
            .handleVisibility(!isNotLiked)
        if (!isNotLiked) {
            currentLikesAmount++
        } else {
            currentLikesAmount--
        }
        itemLikes.text = "$currentLikesAmount"
    }

}