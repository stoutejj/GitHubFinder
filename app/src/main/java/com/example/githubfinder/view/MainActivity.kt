package com.example.githubfinder.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubfinder.R
import com.example.githubfinder.model.RepoInfo
import com.example.githubfinder.model.User
import com.example.githubfinder.viewmodel.GitViewModel
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity(), UserAdapter.UserClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val gitViewModel = ViewModelProvider(this)[GitViewModel::class.java]

        val userRecyclerAdapter = UserAdapter(this)
        user_recycler.layoutManager = LinearLayoutManager(this@MainActivity)
        user_recycler.adapter = userRecyclerAdapter

        gitViewModel.userSearchList.observe(this, Observer {
            userRecyclerAdapter.setUserInfo(it)
            })

        et_search_bar.addTextChangedListener(object : TextWatcher {
            var timer = Timer()
            override fun afterTextChanged(s: Editable?) {
                timer = Timer()
                timer.schedule(object : TimerTask() {
                    override fun run() {
                        // Runs a search on the user after 1 sec
                        if(!s.toString().isNullOrEmpty()){
                            gitViewModel.getUserNameSearch(s.toString())
                        }
                    }
                }, 1000)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                timer.cancel()
            }
        })

    }

    override fun onUserClicked(userName: String) {

        val gitViewModel = ViewModelProvider(this)[GitViewModel::class.java]
        gitViewModel.getUserInfo(userName)

        gitViewModel.userInfo.observe(this, Observer {

          val intent =
                    Intent(this, UserDetails::class.java)
                            .putExtra("userInfo", it)
            startActivity(intent)
        })
    }
}




