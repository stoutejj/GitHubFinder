package com.example.githubfinder.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubfinder.model.*
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import retrofit2.Response

private const val TAG = "GitViewModel"

class GitViewModel : ViewModel() {

/*    val userDao : UserCollectionDao
            = UserDB.getDatabase(
                CustomApplication.getApplication() as Context
            ),getDao()*/

    //private val _userList = MutableLiveData<MutableList<UserInfo>>()
    //val userList: LiveData<MutableList<UserInfo>> = _userList

    private val _userSearchList = MutableLiveData<List<User>>()
    val userSearchList: LiveData<List<User>> = _userSearchList

    private val _repoList = MutableLiveData<List<RepoInfo>>()
    val repoList: LiveData<List<RepoInfo>> = _repoList

    private val _userInfo = MutableLiveData<UserInfo>()
    val userInfo: LiveData<UserInfo> = _userInfo

    private val apiService = GitApiService.create()

    fun getUserNameSearch(userName: String) {
        viewModelScope.launch(IO) {
            val response = apiService.getUserNameSearch(userName).body()

            withContext(Main) {
                _userSearchList.postValue(response?.items)
            }
        }
    }

    fun getUserInfo(userURL: String) {
        viewModelScope.launch(IO) {
            val response = apiService.getUserInfo(userURL).body()

            withContext(Main) {
                _userInfo.postValue(response)
            }
        }
    }

    fun getUserRepo(userName: String) {
        viewModelScope.launch(IO) {
            val response = apiService.getUserRepo(userName).body()

            withContext(Main) {
                _repoList.postValue(response)
            }
        }
    }
}