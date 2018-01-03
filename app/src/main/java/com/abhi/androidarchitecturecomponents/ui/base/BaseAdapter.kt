package com.abhi.androidarchitecturecomponents.ui.base

import android.support.v7.widget.RecyclerView

abstract class BaseAdapter<VH: RecyclerView.ViewHolder, in D>:  RecyclerView.Adapter<VH>() {

    abstract fun setData(dataList: List<D>)
}