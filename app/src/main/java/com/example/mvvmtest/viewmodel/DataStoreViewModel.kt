package com.example.mvvmtest.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmtest.data.datastore.DataStoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class DataStoreViewModel @Inject constructor(
    private val repository: DataStoreRepository
) : ViewModel() {

    companion object {
        const val USER_PHONE_KEY = "USER_PHONE_KEY"
    }

    fun saveUserPhone(phone: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.putString(USER_PHONE_KEY, phone)
        }
    }

    val userPhone = MutableStateFlow("")

    fun getUserPhone() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getString(USER_PHONE_KEY)?.let { phone ->
                userPhone.emit(phone)
            }
        }
    }

    // Second solution -- should be used inside a Coroutines
    suspend fun getUserPhone2(): String? = repository.getString(USER_PHONE_KEY)

}