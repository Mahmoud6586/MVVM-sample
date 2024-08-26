package com.example.mvvmtest.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.mvvmtest.data.model.Post
import com.example.mvvmtest.data.remote.ApiInterface
import com.example.mvvmtest.data.repository.PostsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    private val repository: PostsRepository
) : ViewModel() {
    val postList = MutableLiveData<List<Post>>()
    val postListError = MutableLiveData<String?>()
    val loading = MutableLiveData<Boolean>()

    fun getAllPostsRequest() {
        loading.value = true

        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getAllPosts()

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