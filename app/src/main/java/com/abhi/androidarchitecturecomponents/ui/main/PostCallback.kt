package com.emrekose.karchi.ui.main

import com.abhi.androidarchitecturecomponents.data.local.entity.FakePost

/**
 * post on click event
 */
interface PostCallback {
    fun onPostClick(posts: FakePost?)
}