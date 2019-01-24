package com.sporksoft.surfscrib.ui.adapter

import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import com.sporksoft.surfscrib.R
import com.sporksoft.surfscrib.data.Photo
import com.sporksoft.surfscrib.databinding.ListItemFeedBinding

class FeedAdapter: ListAdapter<Photo, FeedAdapter.ViewHolder>(PhotoDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ListItemFeedBinding.inflate(
                LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position).let { photo ->
            holder.apply {
                bind(createOnClickListener(photo.id), photo)
                itemView.tag = photo
            }
        }
    }

    private fun createOnClickListener(id: String): View.OnClickListener {
        return View.OnClickListener {
            Navigation.findNavController(it).navigate(R.id.detailsFragment)
        }
    }

    class ViewHolder(
            private val binding: ListItemFeedBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(listener: View.OnClickListener, item: Photo) {
            binding.apply {
                clickListener = listener
                photo = item
                executePendingBindings()
            }
        }
    }
}

private class PhotoDiffCallback : DiffUtil.ItemCallback<Photo>() {

    override fun areItemsTheSame(
            oldItem: Photo,
            newItem: Photo): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
            oldItem: Photo,
            newItem: Photo): Boolean {
        return oldItem == newItem
    }
}