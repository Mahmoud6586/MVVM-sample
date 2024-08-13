package com.example.mvvmtest.data.remote

import com.example.mvvmtest.data.model.Post
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {

    @GET("/posts")
    fun getAllPosts(): Response<List<Post>>
}