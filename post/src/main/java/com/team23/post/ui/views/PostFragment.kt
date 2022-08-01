package com.team23.post.ui.views

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
import com.google.android.material.divider.MaterialDivider
import com.team23.core.extensions.handleVisibility
import com.team23.core.extensions.navigateToUser
import com.team23.post.R
import com.team23.post.ui.viewmodels.PostViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PostFragment: Fragment() {
    @Inject
    lateinit var viewModelAssistedFactory: PostViewModel.Factory
    private lateinit var postViewModel: PostViewModel

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
        initViews()
        initObservers()
    }

    private fun initViews() {
        requireView().findViewById<Toolbar>(R.id.post_toolbar).setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun initObservers() {
        postViewModel.isLoading.observe(viewLifecycleOwner) {
            requireView().findViewById<ProgressBar>(R.id.progress_post).handleVisibility(it)
            requireView().findViewById<MaterialDivider>(R.id.post_picture_divider).handleVisibility(!it)
            requireView().findViewById<LinearLayout>(R.id.likes_tag).handleVisibility(!it)
            requireView().findViewById<LinearLayout>(R.id.comments_tag).handleVisibility(!it)
            requireView().findViewById<LinearLayout>(R.id.tags_tag).handleVisibility(!it)
        }
        postViewModel.post.observe(viewLifecycleOwner) {
            requireView().findViewById<ImageView>(R.id.post_picture).setImageBitmap(it.postPicture)
            requireView().findViewById<ImageView>(R.id.user_picture).apply {
                this.setImageBitmap(it.userPicture)
                this.setOnClickListener { _ ->
                    findNavController().navigateToUser(it.userId)
                }
            }
            requireView().findViewById<TextView>(R.id.user_name).text = it.username
            requireView().findViewById<TextView>(R.id.post_date).text = it.postDate
            requireView().findViewById<TextView>(R.id.post_description).text = it.postDescription
            requireView().findViewById<TextView>(R.id.item_likes_amount).text = it.likesAmount.toString()
            requireView().findViewById<TextView>(R.id.item_comments_amount).text = it.commentsAmount.toString()
            requireView().findViewById<TextView>(R.id.item_tags_amount).text = it.tagsAmount.toString()
        }
    }

}