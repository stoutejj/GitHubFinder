package com.example.githubfinder.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.githubfinder.R
import com.example.githubfinder.model.User
import com.example.githubfinder.model.UserInfo
import com.squareup.picasso.Picasso

class UserAdapter (val userClickListener: UserClickListener) :

    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    var dataSet: List<User> = emptyList()

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

    fun setUserInfo(userInfo: List<User>) {
        dataSet = userInfo
        notifyDataSetChanged()
    }

    override fun getItemCount() = dataSet.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {

        holder.onBind(dataSet[position], position, userClickListener)
    }

    class UserViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        var ivUserIcon: ImageView =
            itemView.findViewById(R.id.iv_user_icon)

        var tvUserName: TextView =
            itemView.findViewById(R.id.tv_user_name)

        var tvRepoCount: TextView =
            itemView.findViewById(R.id.tv_repo_count)

        fun onBind(user: User, position: Int, userClickListener: UserClickListener) {

            Picasso.get().load(user.avatar_url).into(ivUserIcon)
            tvUserName.text = user.login

            itemView.setOnClickListener { userClickListener.onUserClicked(user.login) }
        }
    }

    interface UserClickListener {
        fun onUserClicked(userName: String)
    }
}