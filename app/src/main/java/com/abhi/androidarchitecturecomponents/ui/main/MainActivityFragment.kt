package com.abhi.androidarchitecturecomponents.ui.main

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.abhi.androidarchitecturecomponents.R
import com.abhi.androidarchitecturecomponents.data.local.entity.FakePost
import com.abhi.androidarchitecturecomponents.databinding.FragmentMainBinding
import com.abhi.androidarchitecturecomponents.ui.base.BaseFragment
import com.emrekose.karchi.ui.main.PostCallback

/**
 * @author Abhishek Prajapati
 * @version 1.0.0
 * @since 1/2/18.
 *
 * This is a main fragment that is attached to main activity (through xml)
 */
class MainActivityFragment : BaseFragment<MainViewModel, FragmentMainBinding>(), PostCallback {

    private var adapter = PostListRecyclerAdapter(this)

    override fun getViewModel(): Class<MainViewModel> = MainViewModel::class.java

    override fun getLayoutRes(): Int = R.layout.fragment_main

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        dataBinding.progressBar.visibility = View.VISIBLE
        dataBinding.postListRecycler.layoutManager = LinearLayoutManager(activity)
        dataBinding.postListRecycler.adapter = adapter

        return dataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.getPostsList()
                .observe(this, Observer {
                    it?.let {
                        // println(it)
                        adapter.setData(it)
                        dataBinding.progressBar.visibility = View.GONE
                    }
                })
    }

    override fun onPostClick(posts: FakePost?) {
        Toast.makeText(context,posts?.title, Toast.LENGTH_SHORT).show()
    }
}
