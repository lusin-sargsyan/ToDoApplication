package com.example.todoapplication.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapplication.databinding.ItemTitlesListBinding
import com.example.todoapplication.domain.entities.Title
import com.example.todoapplication.presentation.ui.home_page.TitleViewModel

class TitleAdapter(private val viewModel: TitleViewModel, private val click: (Long) -> Unit) :
    ListAdapter<Title, TitleAdapter.MyViewHolder>(DiffUtilItemCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemTitlesListBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.binding.root.setOnClickListener {
            click(getItem(position).id)
        }
    }

    inner class MyViewHolder(val binding: ItemTitlesListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(title: Title) {
            with(binding) {
                titleText.text = title.title
                icTitleDelete.setOnClickListener {
                    viewModel.deleteTitle(title)
                }
            }
        }
    }

    class DiffUtilItemCallBack : DiffUtil.ItemCallback<Title>() {

        override fun areItemsTheSame(oldItem: Title, newItem: Title): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Title, newItem: Title): Boolean =
            oldItem == newItem

    }
}