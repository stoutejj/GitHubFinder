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
import io.reactivex.internal.schedulers.IoScheduler
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.username_item_layout.*
import kotlinx.android.synthetic.main.username_item_layout.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
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

        println("USER SEARCH LIST 0: " + gitViewModel.userSearchList.value)

        gitViewModel.getUserNameSearch("antonino")

        gitViewModel.userInfoList.observe(this, Observer {

            //val useriList: List<User> = it

            //var list = mutableListOf<UserInfo>()
            //val userInfoList: MutableList<UserInfo> = mutableListOf()
             //val userInfoList: MutableList<UserInfo> = mutableListOf()
            //var userInfoList = arrayListOf<UserInfo>()

           // userRecyclerAdapter.setUserInfo(it)

                //println("USER INFO ----> " + gitViewModel.userInfo)
                //
                //sleep(2000)

                //userInfoList.add(gitViewModel.getUser())
            }

            //println("USER LIST ADDED: " + gitViewModel.userList.value.toString())
            //println("USER INFO LIST FROM LOOP" + userInfoList)
            //println("USER INFO LIST FROM VIEW MODEL" + gitViewModel.userInfoList)
            //println("userInfoList: " + userInfoList)

            //println("gitViewModel.userInfoList" + gitViewModel.userInfoList)


            //
        )

        et_search_bar.addTextChangedListener(object : TextWatcher {
            var timer = Timer()
            override fun afterTextChanged(s: Editable?) {
                timer = Timer()
                timer.schedule(object : TimerTask() {
                    override fun run() {

                           // gitViewModel.getUserNameSearch(s.toString())

                    }
                }, 2000)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                timer.cancel()
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




