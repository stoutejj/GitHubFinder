package com.example.githubfinder.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.githubfinder.R
import com.example.githubfinder.model.User
import com.example.githubfinder.model.UserInfo

/*
class UserAdapter :

    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    var dataSet: List<UserInfo> = UserInfo(emptyList())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : UserViewHolder =
        UserViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(
                    R.layout.username_item_layout,
                    parent,
                    false
                )
        )

    fun setUserInfo(t: List<UserInfo>) {
        dataSet = t
        notifyDataSetChanged()
    }

    override fun getItemCount() = dataSet.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.onBind(dataSet, position)
    }

    class UserViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        var imUserIcon: ImageView =
            itemView.findViewById(R.id.iv_user_icon)

        var tvUserName: TextView =
            itemView.findViewById(R.id.tv_user_name)

        var tvRepoCount: TextView =
            itemView.findViewById(R.id.tv_repo_count)

        fun onBind(data: User, position: Int) {
            imUserIcon.text = data.url

        }
    }
}*/