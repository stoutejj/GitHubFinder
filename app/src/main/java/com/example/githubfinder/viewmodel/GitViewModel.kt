package com.example.githubfinder.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubfinder.model.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GitViewModel : ViewModel() {

    private val _userList = MutableLiveData<List<User>>()
    val userList: LiveData<List<User>> = _userList

    private val _userSearchList = MutableLiveData<List<User>>()
    val userSearchList: LiveData<List<User>> = _userSearchList

    private val _repoList = MutableLiveData<List<RepoInfo>>()
    val repoList: LiveData<List<RepoInfo>> = _repoList

    //private val _userInfoList = MutableLiveData<List<UserInfo>>()
    //val userInfoList: LiveData<List<UserInfo>> = _userInfoList

    lateinit var userInfo: UserInfo

    private val baseApiUrl: String = "https://api.github.com/"


    fun getUserList() {
        val network = Network(baseApiUrl)
        network.initRetrofit().getUserList()
            .enqueue(object : Callback<List<User>> {
                override fun onResponse(
                    call: Call<List<User>>,
                    response: Response<List<User>>
                ) {
                    //println("getUserList SUCCESS")
                    // println(response.body().toString())
                    _userList.value = response.body()
                }

                override fun onFailure(call: Call<List<User>>, t: Throwable) {
                    println("getUserList FAILURE")
                    t.printStackTrace()
                }
            })
    }

    fun getUserNameSearch(userName: String) {
        val network = Network(baseApiUrl)
        network.initRetrofit().getUserNameSearch(userName)
            .enqueue(object : Callback<ItemList> {
                override fun onResponse(
                    call: Call<ItemList>,
                    response: Response<ItemList>
                ) {
                    println("getUserNameSearch SUCCESS")
                    println(response.body().toString())
                    _userSearchList.value = response.body()?.items

                    val userURL: List<User> = _userSearchList.value!!

                    val userInfoList: MutableList<UserInfo> = mutableListOf()

                    for (user in userURL) {

                       println("USERNAME = " + user.url.takeLastWhile { !it.equals('/') }) // TAKES THE USERNAME SUBSTRING FROM THE USER URL -->>> getUserInfo(USERNAME)
                        //getUserInfo(user.url.takeLastWhile { !it.equals("/") })
                        //userInfo

                        //userInfoList.add(userInfo)
                    }
                }

                override fun onFailure(call: Call<ItemList>, t: Throwable) {
                    println("getUserNameSearch FAILURE")
                    t.printStackTrace()
                }
            })
    }


    fun getUserInfo(userURL: String) {
        val network = Network(baseApiUrl)
        network.initRetrofit().getUserInfo(userURL)
            .enqueue(object : Callback<UserInfo> {
                override fun onResponse(
                    call: Call<UserInfo>,
                    response: Response<UserInfo>
                ) {
                    userInfo = response.body()!!

                }

                override fun onFailure(call: Call<UserInfo>, t: Throwable) {
                    println("getUserNameSearch FAILURE")
                    t.printStackTrace()
                }
            })
    }

    fun getPublicRepo(userName: String) {
        val network = Network(baseApiUrl)
        network.initRetrofit().getPublicRepo(userName)
            .enqueue(object : Callback<List<RepoInfo>> {
                override fun onResponse(
                    call: Call<List<RepoInfo>>,
                    response: Response<List<RepoInfo>>
                ) {
                    //println("getUserNameSearch SUCCESS")
                    // println(response.body().toString())
                    _repoList.value = response.body()
                }

                override fun onFailure(call: Call<List<RepoInfo>>, t: Throwable) {
                    println("getUserNameSearch FAILURE")
                    t.printStackTrace()
                }
            })
    }

}

