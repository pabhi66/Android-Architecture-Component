package com.abhi.androidarchitecturecomponents.ui.base

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.annotation.MenuRes
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

/**
 * @author Abhishek Prajapati
 * @version 1.0.0
 * @since 1/2/18.
 */
abstract class BaseActivity<DB: ViewDataBinding>: AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    lateinit var dataBinding: DB

    @LayoutRes
    abstract fun getLayoutRes(): Int

    @MenuRes
    open fun getMenuRes(): Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, getLayoutRes())
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = fragmentDispatchingAndroidInjector

}