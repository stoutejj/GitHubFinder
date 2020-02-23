package com.example.githubfinder.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.githubfinder.R
import com.example.githubfinder.model.UserInfo
import com.squareup.picasso.Picasso


class UserAdapter :

    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    var dataSet: List<UserInfo> = emptyList()

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
        holder.onBind(dataSet[position], position)
    }

    class UserViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        var ivUserIcon: ImageView =
            itemView.findViewById(R.id.iv_user_icon)

        var tvUserName: TextView =
            itemView.findViewById(R.id.tv_user_name)

        var tvRepoCount: TextView =
            itemView.findViewById(R.id.tv_repo_count)

        fun onBind(data: UserInfo, position: Int) {

            var userIcon: String = data.avatar_url

            Picasso.get().load("http://openweathermap.org/img/wn/$userIcon@2x.png").into(ivUserIcon)
            tvUserName.text = data.login
            tvRepoCount.text = data.public_repos.toString()
        }
    }
}