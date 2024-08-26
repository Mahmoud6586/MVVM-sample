package com.example.mvvmtest.data.repository

import com.example.mvvmtest.data.remote.ApiInterface
import javax.inject.Inject

class PostsRepository @Inject constructor(
    private val api: ApiInterface
) {

    suspend fun getAllPosts() = api.getAllPosts()
}