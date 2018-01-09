package com.abhi.androidarchitecturecomponents.di.modules

import com.abhi.androidarchitecturecomponents.ui.main.MainActivity
import com.abhi.androidarchitecturecomponents.ui.main.search.SearchActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * @author Abhishek Prajapati
 * @version 1.0.0
 * @since 1/2/18.
 *
 * this class will include all activities in our app
 */
@Module
abstract class ActivityModule {

    @ContributesAndroidInjector(modules = arrayOf(FragmentModule::class))
    abstract fun mainActivity(): MainActivity

    @ContributesAndroidInjector(modules = arrayOf(FragmentModule::class))
    abstract fun searchActivity(): SearchActivity
}