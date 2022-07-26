package com.team23.user.ui.views

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.findNavController
import com.team23.user.ui.themes.UserTheme
import com.team23.user.ui.viewmodels.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class UserFragment : Fragment() {
    @Inject
    lateinit var viewModelAssistedFactory: UserViewModel.Factory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)

        val userViewModel: UserViewModel by viewModels {
            UserViewModel.provideFactory(viewModelAssistedFactory, "60d0fe4f5311236168a109ca")
        }

        setContent {
            UserTheme {
                UserProfile(userViewModel = userViewModel, navigateToSearch = {
                    findNavController().navigate(
                        NavDeepLinkRequest.Builder
                            .fromUri("socialProfile://home".toUri())
                            .build())
                })
            }
        }
    }
}