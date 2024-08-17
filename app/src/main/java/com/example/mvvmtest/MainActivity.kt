package com.example.mvvmtest

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ReportFragment.Companion.reportFragment
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmtest.data.model.Post
import com.example.mvvmtest.ui.theme.MvvmTestTheme
import com.example.mvvmtest.viewmodel.PostViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MvvmTestTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        ObserverPostsViewModel()
                    }

                }
            }
        }
    }

    @Composable
    private fun ObserverPostsViewModel() {


        var postList by remember {
            mutableStateOf(emptyList<Post>())
        }

        Column {
            PostView(postList = postList)
        }

        /// It is added to prevent below services to be call with each change of state
        ///on postList state - The services are called only once

        LaunchedEffect(key1 = Unit) {

            val viewModel = ViewModelProvider(this@MainActivity).get(PostViewModel::class.java)
            viewModel.getAllPostsRequest()

            viewModel.postList.observe(this@MainActivity) { posts -> postList = posts }

            viewModel.postListError.observe(this@MainActivity) { isError ->
                isError?.let {
                    Log.e("3636", isError)
                }
            }

            viewModel.loading.observe(this@MainActivity) { isLoading ->
                Log.e("3636", isLoading.toString())
            }
        }

        @Composable
        fun PostView(postList: List<Post>) {
            LazyColumn {
                items(postList) { post ->
                    Column(
                        modifier = Modifier
                            .padding(12.dp)
                            .background(Color.Blue)
                    ) {
                        Text(text = post.title, color = Color.White)
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(text = post.body, color = Color.DarkGray)
                    }
                }
            }
        }


    }
}

@Composable
fun PostView(postList: List<Post>) {
    LazyColumn {
        items(postList) { post ->
            Column(
                modifier = Modifier
                    .padding(12.dp)
                    .background(Color.Blue)
            ) {
                Text(text = post.title, color = Color.White)
                Spacer(modifier = Modifier.height(12.dp))
                Text(text = post.body, color = Color.DarkGray)
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MvvmTestTheme {
        Greeting("Android")
    }
}