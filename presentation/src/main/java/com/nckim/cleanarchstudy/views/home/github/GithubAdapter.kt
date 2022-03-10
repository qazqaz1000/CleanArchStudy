package com.nckim.cleanarchstudy.views.home.github

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nckim.cleanarchstudy.R
import com.nckim.cleanarchstudy.databinding.RecyclerGithubItemBinding
import com.nckim.cleanarchstudy.views.search.MovieAdapter
import com.nckim.domain.model.github.GithubRepositoryModel
import com.nckim.domain.model.movie.Movie

class GithubAdapter(private val itemClick: (GithubRepositoryModel) -> Unit) : ListAdapter<GithubRepositoryModel, GithubAdapter.ViewHolder>(
    diffUtil
){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: RecyclerGithubItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.recycler_github_item,
            parent,
            false
        )
        return ViewHolder(binding).apply {
            binding.root.setOnClickListener{ view ->
                //예외
                itemClick(getItem(adapterPosition))
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(private val binding: RecyclerGithubItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(github: GithubRepositoryModel) {
            binding.github = github
            binding.executePendingBindings()
        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<GithubRepositoryModel>() {
            override fun areContentsTheSame(oldItem: GithubRepositoryModel, newItem: GithubRepositoryModel) =
                oldItem == newItem

            override fun areItemsTheSame(oldItem: GithubRepositoryModel, newItem: GithubRepositoryModel) =
                oldItem.id == newItem.id
        }
    }

}