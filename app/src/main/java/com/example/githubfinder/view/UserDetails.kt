package com.example.githubfinder.view

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubfinder.R
import com.example.githubfinder.model.RepoInfo
import com.example.githubfinder.model.UserInfo
import com.example.githubfinder.viewmodel.GitViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_user_details.*
import java.util.*

class UserDetails : AppCompatActivity(), RepoAdapter.RepoClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_details)

        val repoRecyclerAdapter = RepoAdapter(this)
        repo_recycler.layoutManager = LinearLayoutManager(this@UserDetails)
        repo_recycler.adapter = repoRecyclerAdapter

        val userInfo = intent.extras?.getParcelable<UserInfo>("userInfo")
        val gitViewModel = ViewModelProvider(this)[GitViewModel::class.java]

        Picasso.get().load(userInfo?.avatar_url).into(repo_user_icon)
        repo_user_name.text = "UserName: " + userInfo?.login
        repo_user_email.text = "Email: " + userInfo?.email
        repo_user_location.text = "Location: " + userInfo?.location


        val date = StringBuilder()
        date.append(
                userInfo?.created_at?.substring(5, 7) + "/" +
                        userInfo?.created_at?.substring(8, 10) + "/" +
                        userInfo?.created_at?.substring(0, 4))

        repo_user_join_date.text = "Join Date: " + date
        repo_user_followers.text = userInfo?.followers.toString() + " Followers"
        repo_user_following.text = "Following " + userInfo?.followers.toString()
        repo_user_bio.text = userInfo?.login

        userInfo?.login?.let { gitViewModel.getUserRepo(it) }
        gitViewModel.repoList.observe(this, Observer {
            println("REPOS: " + it)
            repoRecyclerAdapter.setRepo(it)
        })

        repo_search_bar.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                filter(gitViewModel.repoList.value,s.toString())?.let {
                    repoRecyclerAdapter.setRepo(
                            it)
                }
                //gitViewModel.getUserNameSearch(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
    }

    fun filter(repoList : List<RepoInfo>?, searchInput : String) : List<RepoInfo>?{

        var newList: ArrayList<RepoInfo> = arrayListOf()

        if(searchInput.isEmpty()){
            println("EMPTY:" + repoList)
            return repoList
        }
        else{
            if (repoList != null) {
                for(repoInfo in repoList ){
                    if(repoInfo.name.contains(searchInput,true)){
                        println("NEW LIST:" + repoList)
                        newList.add(repoInfo)
                    }
                }
            }
            return newList
        }
    }


    override fun onRepoClicked(repoUrl: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.setData(Uri.parse(repoUrl))
        startActivity(intent)
    }

}
