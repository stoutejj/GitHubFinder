package com.example.githubfinder.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubfinder.R
import com.example.githubfinder.model.ItemList
import com.example.githubfinder.model.User
import com.example.githubfinder.viewmodel.GitViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val gitViewModel = ViewModelProvider(this)[GitViewModel::class.java]
        /*
        val userRecyclerAdapter = UserAdapter()
        user_recycler.layoutManager = LinearLayoutManager(this@MainActivity)
        user_recycler.adapter = userRecyclerAdapter   */

        //gitViewModel.getUserList()
        //gitViewModel.getUserNameSearch("tom")
        //gitViewModel.getUserInfo("tom")
        //gitViewModel.getPublicRepo("tom")

        var etSearchUser: EditText =
            findViewById(R.id.et_search_bar)

        etSearchUser.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                //println("Output  " + gitViewModel.userSearchList.value)
                gitViewModel.getUserNameSearch(s.toString())

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })


    }
}
