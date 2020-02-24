package com.example.githubfinder.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubfinder.model.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.android.schedulers.AndroidSchedulers.*
import io.reactivex.schedulers.Schedulers
import io.reactivex.schedulers.Schedulers.*

class GitViewModel : ViewModel() {

    private val _userList = MutableLiveData<MutableList<UserInfo>>()
    val userList: LiveData<MutableList<UserInfo>> = _userList

    private val _userSearchList = MutableLiveData<List<User>>()
    val userSearchList: LiveData<List<User>> = _userSearchList

    private val _repoList = MutableLiveData<List<RepoInfo>>()
    val repoList: LiveData<List<RepoInfo>> = _repoList

    val userInfoList: MutableList<UserInfo> = mutableListOf()

    lateinit var userInfo: UserInfo

    @SuppressLint("CheckResult")
    fun getUserNameSearch(userName: String) {
        val baseApiUrl = "https://api.github.com/"
        val network = Network(baseApiUrl)
        network.initRetrofit().getUserNameSearch(userName)
            .subscribeOn(io())
            .unsubscribeOn(computation())
            .observeOn(mainThread())
            .subscribe({
                _userSearchList.value = it.items
                println("GET USER NAME SEARCH: " + _userSearchList.value)

            }, {
                println("GET USER NAME SEARCH ERROR")
            })
    }


    fun setUserInfo(user : UserInfo) : UserInfo {
        userInfo = user

        println("SET USER INFO : " + userInfo.login)

        return userInfo
    }


        @SuppressLint("CheckResult")
    fun getUserInfo(userURL: String){
        val baseApiUrl = "https://api.github.com/"
        val network = Network(baseApiUrl)
        network.initRetrofit().getUserInfo(userURL)
            .subscribeOn(io())
            .unsubscribeOn(computation())
            .observeOn(mainThread())
            .subscribe({
                println("GET USER INFO : " + it.login)
                setUserInfo(it)
            }, {
                println("GET USER INFO ERROR")
            })
    }

    @SuppressLint("CheckResult")
    fun getUserRepo(userName: String) {
        val baseApiUrl = "https://api.github.com/"
        val network = Network(baseApiUrl)
        network.initRetrofit().getUserRepo(userName)
            .subscribeOn(io())
            .unsubscribeOn(computation())
            .observeOn(mainThread())
            .subscribe({
                _repoList.value = it
                println("USER REPO LIST: " + _repoList.value)

            }, {
                println("USER REPO ERROR")
            })
    }


/*


    fun getPublicRepo(userName: String) {
        network.initRetrofit().getPublicRepo(userName)
            .enqueue(object : Callback<List<RepoInfo>> {
                override fun onResponse(
                    call: Call<List<RepoInfo>>,
                    response: Response<List<RepoInfo>>
                ) {
                    //println("getPublicRepo SUCCESS")
                    // println(response.body().toString())
                    _repoList.value = response.body()
                }

                override fun onFailure(call: Call<List<RepoInfo>>, t: Throwable) {
                    println("getUserNameSearch FAILURE")
                    t.printStackTrace()
                }
            })
    }*/
}



/*
    fun getUserList() {
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
    }*/




/*
        .enqueue(object : Callback<ItemList> {
            override fun onResponse(
                call: Call<ItemList>,
                response: Response<ItemList>
            ) {
                //println("getUserNameSearch SUCCESS")
                //println(response.body().toString())
                if(response.isSuccessful) {
                    _userSearchList.value = response.body()?.items

                    val userURL: List<User> = _userSearchList.value!!

                    for (user in userURL) {
                        println("USERNAME = " + user.url.takeLastWhile { !it.equals('/') }) // TAKES THE USERNAME SUBSTRING FROM THE USER URL -->>> getUserInfo(USERNAME)

                            getUserInfo(user.url.takeLastWhile { !it.equals('/') })

                        }

                       userInfoList.add(userInfo)
                }
            }

            override fun onFailure(call: Call<ItemList>, t: Throwable) {
                println("getUserNameSearch FAILURE")
                t.printStackTrace()
            }
        })*/



/*
    network.initRetrofit().getUserInfo(userURL)
        .enqueue(object : Callback<UserInfo> {
            override fun onResponse(
                call: Call<UserInfo>,
                response: Response<UserInfo>
            ) {
                //println("getUserInfo SUCCESS")
                response.body()?.let {

                    userInfo = it
                    println("USER LOGIN " + userInfo.login)
                    userInfoList.add(userInfo)
                }
//                    userInfo = response.body()!!

            }

            override fun onFailure(call: Call<UserInfo>, t: Throwable) {
                println("getUserInfo FAILURE")
                t.printStackTrace()
            }
        })
}*/