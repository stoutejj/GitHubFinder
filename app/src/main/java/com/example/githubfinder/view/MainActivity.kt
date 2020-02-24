package com.example.githubfinder.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.os.SystemClock.sleep
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.githubfinder.R
import com.example.githubfinder.model.ItemList
import com.example.githubfinder.model.RepoInfo
import com.example.githubfinder.model.User
import com.example.githubfinder.model.UserInfo
import com.example.githubfinder.viewmodel.GitViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.username_item_layout.*
import kotlinx.android.synthetic.main.username_item_layout.view.*
import java.util.*
import kotlin.concurrent.schedule

class MainActivity : AppCompatActivity(), UserAdapter.UserClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val gitViewModel = ViewModelProvider(this)[GitViewModel::class.java]

        val userRecyclerAdapter = UserAdapter(this)
        user_recycler.layoutManager = LinearLayoutManager(this@MainActivity)
        user_recycler.adapter = userRecyclerAdapter

        /*
        val userRecyclerAdapter = UserAdapter()
        user_recycler.layoutManager = LinearLayoutManager(this@MainActivity)
        user_recycler.adapter = userRecyclerAdapter   */

        gitViewModel.getUserNameSearch("stoutejj")
        //gitViewModel.getUserInfo("tom")
        gitViewModel.userSearchList.observe(this, Observer {
            //gitViewModel.getUserInfo()

            val userList: List<User> = it

            val list = mutableListOf<UserInfo>()
            val userInfoList: MutableList<UserInfo> = mutableListOf()

            for (user in userList) {
                println("USERNAME = " + user.url.takeLastWhile { !it.equals('/') }) // TAKES THE USERNAME SUBSTRING FROM THE USER URL -->>> getUserInfo(USERNAME)
                gitViewModel.getUserInfo(user.url.takeLastWhile { !it.equals('/') })

                println("USER INFO ----> " + gitViewModel.userInfo)

                //userInfoList.add(gitViewModel.userInfo)
            }

            //println("USER LIST ADDED: " + gitViewModel.userList.value.toString())

            userRecyclerAdapter.setUserInfo(userInfoList)
        })

        et_search_bar.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                //println("Output  " + gitViewModel.userSearchList.value)
                //SystemClock.sleep(10000)
                // gitViewModel.getUserNameSearch(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

    }

    override fun onUserClicked(userInfo: UserInfo) {
        println("CLICK")
        val intent =
            Intent(
                this,
                UserDetails::class.java
            )
                .putExtra("userInfo", userInfo)
        startActivity(intent)    }
}




