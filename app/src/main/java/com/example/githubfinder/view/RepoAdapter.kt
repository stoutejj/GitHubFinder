package com.example.githubfinder.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.githubfinder.R
import com.example.githubfinder.model.RepoInfo

class RepoAdapter(val repoClickListener: RepoClickListener) :

    RecyclerView.Adapter<RepoAdapter.RepoViewHolder>() {

    var dataSet: List<RepoInfo> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : RepoViewHolder =
        RepoViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(
                    R.layout.repo_info_item_layout,
                    parent,
                    false
                )
        )

    fun setRepo(repoInfoList: List<RepoInfo>) {
        dataSet = repoInfoList
        notifyDataSetChanged()
    }

    override fun getItemCount() = dataSet.size

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        holder.onBind(dataSet[position], position, repoClickListener)
    }

    class RepoViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        var tvRepoName: TextView =
            itemView.findViewById(R.id.tv_repo_name)

        var tvRepoForks: TextView =
            itemView.findViewById(R.id.tv_user_fork_count)

        var tvRepoStars: TextView =
            itemView.findViewById(R.id.tv_repo_star_count)

        fun onBind(data: RepoInfo, position: Int, repoClickListener: RepoClickListener) {

            tvRepoName.text = data.name
            tvRepoForks.text = data.forks.toString() + " FORKS"
            tvRepoStars.text = data.stargazers_count.toString() + " STARS"

            itemView.setOnClickListener { repoClickListener.onRepoClicked(data.html_url) }
        }
    }

    interface RepoClickListener {
        fun onRepoClicked(repoUrl: String)
    }
}