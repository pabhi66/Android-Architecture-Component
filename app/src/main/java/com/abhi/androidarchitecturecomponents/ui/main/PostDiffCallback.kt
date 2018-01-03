package com.emrekose.karchi.ui.main

import android.support.v7.util.DiffUtil
import com.abhi.androidarchitecturecomponents.data.local.entity.FakePost

class PostDiffCallback(var oldPostList: List<FakePost>, var newPostList: List<FakePost>) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldPostList.size

    override fun getNewListSize(): Int = newPostList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldPostList[oldItemPosition].id == newPostList[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldPostList[oldItemPosition].equals(newPostList[newItemPosition])
}