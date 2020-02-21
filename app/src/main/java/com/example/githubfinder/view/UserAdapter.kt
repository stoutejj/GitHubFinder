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

/*
class UserAdapter :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    var dataSet: WeatherDataList = WeatherDataList(emptyList())

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

    fun setDailyWeatherData(t: WeatherDataList) {
        dataSet = t
        notifyDataSetChanged()
        //Log.d("TESTING TO SEE OUTPUT: ", dataSet.toString())

    }

    override fun getItemCount() = dataSet.list.size

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

        fun onBind(data: WeatherDataList, position: Int) {
            tvDate.text = data.list[position].dt_txt.substring(0, 10) + "   " + position

        }
    }
}
*/