package com.example.postsapp.ui.theme.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.postsapp.data.Repository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class HomeViewModel: ViewModel() {
    private val repository = Repository()
    private val userId = FirebaseAuth.getInstance().currentUser?.uid ?: ""

    val posts = repository.pegarPosts(userId)
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )

}