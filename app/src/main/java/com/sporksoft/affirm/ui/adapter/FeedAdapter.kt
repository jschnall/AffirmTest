package com.sporksoft.affirm.ui.adapter

import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.ViewGroup
import android.view.View
import com.sporksoft.affirm.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_feed.*
import com.sporksoft.affirm.helper.extensions.*
import com.sporksoft.affirm.models.Photo

class FeedAdapter(val items: MutableList<Photo> = ArrayList(), val onClickListener: View.OnClickListener? = null): RecyclerView.Adapter<FeedAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.item_feed), onClickListener)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items.get(position)
        holder.bind(item)
        holder.containerView.tag = position
    }

    class ViewHolder(override val containerView: View, listener: View.OnClickListener? = null) : RecyclerView.ViewHolder(containerView),
            LayoutContainer {

        init {
            containerView.setOnClickListener(listener)
        }

        fun bind(item: Photo) {
            if (!TextUtils.isEmpty(item.thumbUrl)) {
                image.loadUrl(item.thumbUrl)
            }
            title.text = item.title
            subTitle.text = item.id
        }
    }

    fun addPage(newItems: List<Photo>?) {
        if (newItems == null) {
            return
        }
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    fun reset(newItems: List<Photo>?) {
        if (newItems == null) {
            return
        }
        items.clear()
        addPage(newItems)
    }

}