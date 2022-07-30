package com.team23.core.extensions

import androidx.core.net.toUri
import androidx.navigation.NavController
import androidx.navigation.NavDeepLinkRequest

private fun NavController.navigateTo(page: String, userId: String) {
    val request = NavDeepLinkRequest.Builder
        .fromUri("socialProfile://$page/$userId".toUri())
        .build()
    this.navigate(request)
}

fun NavController.navigateToPost(postId: String) = navigateTo("posts", postId)
fun NavController.navigateToUser(userId: String) = navigateTo("users", userId)
