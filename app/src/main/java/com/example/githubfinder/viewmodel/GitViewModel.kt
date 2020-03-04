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

    private val _userInfoList = MutableLiveData<List<UserInfo>>()
    val userInfoList: MutableLiveData<MutableList<UserInfo>> =
        MutableLiveData<MutableList<UserInfo>>()

    private val _userInfo: MutableLiveData<UserInfo> = MutableLiveData()
    val userInfo: LiveData<UserInfo> = _userInfo

    fun getUserNameSearch(userName: String) {
        val baseApiUrl = "https://api.github.com/"
        val network = Network(baseApiUrl)

        println("STARTING USER SEARCH....")
        println("USER SEARCH LIST 1: " + _userSearchList.value)

        viewModelScope.launch(IO){

            println("VIEW MODEL SCOPE")

            val response = network.initRetrofit().getUserNameSearch(userName)
            if (response.isSuccessful) {
                _userSearchList.value =
                    response.body()?.items
                println("USER SEARCH LIST : " + _userSearchList.value)
            }
            else{
                println("FAILURE")
            }
        }




        /* viewModelScope.launch(IO) {
             println("ENTERING VIEW MODEL SCOPE....")


            // Log.d(TAG, userSearchList.toString())

            // _userSearchList.postValue(userSearchList)

             //println("USER SEARCH LIST 3: ${userSearchList.size}")

             //val userList = network.initRetrofit().getUserNameSearch(userName).items

            // println("USER SEARCH LIST 4: " + userList)

 //
 //
            val userList: List<User> = _userSearchList.value!!

 //                for (user in userList) {
 //                    getUserInfo(user.url.takeLastWhile { !it.equals('/') })
 //                }
 //                val deferred:List<Deferred<List<UserInfo>>> = userList.map { user ->
 //                    async { getUserInfo(user.url.takeLastWhile { it != '/' })}
 //
 //                }
 //
                 val deferred: List<Deferred<UserInfo>> = userList.map { user ->
                     async { getUserInfo(user.url.takeLastWhile { it != '/' })}

                 }
                 _userInfoList.postValue(deferred.awaitAll())
         }*/
    }

    fun setUserInfo(userInfo: UserInfo) {
        val update = userInfo
        if (_userInfo.value == update) {
            return
        }
        _userInfo.value = update
    }


    suspend fun getUserInfo(userURL: String): UserInfo {
        val baseApiUrl = "https://api.github.com/"
        val network = Network(baseApiUrl)
        println("STARTING GET USER INFO ....")
        return network.initRetrofit().getUserInfo(userURL)
    }

    suspend fun getUserRepo(userName: String) {
        val baseApiUrl = "https://api.github.com/"
        val network = Network(baseApiUrl)
        withContext(IO) {
            _repoList.value = network.initRetrofit().getUserRepo(userName)
        }
    }

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