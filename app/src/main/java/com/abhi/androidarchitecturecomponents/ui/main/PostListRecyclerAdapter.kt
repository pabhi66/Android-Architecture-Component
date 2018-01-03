package com.abhi.androidarchitecturecomponents.ui.main

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.abhi.androidarchitecturecomponents.R
import com.abhi.androidarchitecturecomponents.data.local.entity.FakePost
import com.abhi.androidarchitecturecomponents.databinding.ItemPostBinding
import com.emrekose.karchi.ui.main.PostCallback
import com.emrekose.karchi.ui.main.PostDiffCallback

/**
 * @author Abhishek Prajapati
 * @version 1.0.0
 * @since 1/3/18.
 */

class PostListRecyclerAdapter(var callback: PostCallback) : RecyclerView.Adapter<PostListRecyclerAdapter.PostViewHolder>() {

    private var postsList: ArrayList<FakePost> = ArrayList()

    fun setData(posts: List<FakePost>) {
        val diffCallback = DiffUtil.calculateDiff(PostDiffCallback(postsList, posts))
        with(postsList) {clear(); addAll(posts)}
        diffCallback.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): PostViewHolder =
            PostViewHolder.create(LayoutInflater.from(parent?.context), parent, callback)

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = postsList[position]
        holder.onBind(post)
    }

    override fun getItemCount(): Int = postsList.size

    class PostViewHolder(val itemPostBinding: ItemPostBinding, val callback: PostCallback): RecyclerView.ViewHolder(itemPostBinding.root) {

        companion object {
            fun create(layoutInflater: LayoutInflater, parent: ViewGroup?, callback: PostCallback): PostViewHolder {
                val itemPostBinding = ItemPostBinding.inflate(layoutInflater, parent, false)
                return PostViewHolder(itemPostBinding, callback)
            }
        }

        init {
            itemPostBinding.root.setOnClickListener {
                callback.let { callback.onPostClick(itemPostBinding.post) }
            }
        }
        fun onBind(posts: FakePost) {
            itemPostBinding.apply {
                post = posts
                executePendingBindings()
            }
        }
    }

}
