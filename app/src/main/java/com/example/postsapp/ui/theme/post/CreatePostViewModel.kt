package com.example.postsapp.ui.theme.post

import androidx.lifecycle.ViewModel
import com.example.postsapp.data.Repository
import com.google.firebase.auth.FirebaseAuth

class CreatePostViewModel: ViewModel() {
    private val repository = Repository()
    private val  userId = FirebaseAuth.getInstance().currentUser?.uid ?: ""


    fun createPost(descricao: String, imagem: String) {
        repository.salvarPost(userId, descricao, imagem)
    }
}