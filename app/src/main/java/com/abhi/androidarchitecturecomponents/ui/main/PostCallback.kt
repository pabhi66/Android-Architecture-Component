package com.emrekose.karchi.ui.main

import com.abhi.androidarchitecturecomponents.data.local.entity.FakePost


interface PostCallback {
    fun onPostClick(posts: FakePost?)
}