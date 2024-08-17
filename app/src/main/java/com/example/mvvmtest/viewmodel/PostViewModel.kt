package com.example.mvvmtest.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.mvvmtest.data.model.Post
import com.example.mvvmtest.data.remote.ApiClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PostViewModel : ViewModel() {
    val postList = MutableLiveData<List<Post>>()
    val postListError = MutableLiveData<String?>()
    val loading = MutableLiveData<Boolean>()

    fun getAllPostsRequest() {
        loading.value = true

        viewModelScope.launch(Dispatchers.IO) {
            val response = ApiClient.api.getAllPosts()

            withContext(Dispatchers.Main) {
                if (response.isSuccessful && response.body() != null) {
                    response.body()?.let { allPosts ->
                        postList.value = allPosts
                        postListError.value = null
                        loading.value = false
                    }
                } else {
                    postListError.value = response.message()
                    loading.value = false
                }
            }

        }


    }
}