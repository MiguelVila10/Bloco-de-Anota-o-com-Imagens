package com.example.postsapp.ui.theme.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.postsapp.data.Post
import com.example.postsapp.data.Repository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val postId: String
) : ViewModel() {
    private val repository = Repository()
    private val userId = FirebaseAuth.getInstance().currentUser?.uid ?: ""

    var post = MutableStateFlow<Post?>(null)
        private set

    init {
        viewModelScope.launch {
            post.value = repository.pegarPostPorId(userId, postId)
        }
    }
}