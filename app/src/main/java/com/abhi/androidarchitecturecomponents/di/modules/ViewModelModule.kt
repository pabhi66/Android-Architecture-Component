package com.abhi.androidarchitecturecomponents.di.modules

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.abhi.androidarchitecturecomponents.VMFactory
import com.abhi.androidarchitecturecomponents.di.scopes.ViewModelKey
import com.abhi.androidarchitecturecomponents.ui.main.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * @author Abhishek Prajapati
 * @version 1.0.0
 * @since 1/2/18.
 *
 * this will inject/binds all the view models in our app
 */

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindsMainViewModel(MainViewModel: MainViewModel): ViewModel

    @Binds
    abstract fun bindsViewModelFactory(vmFactory: VMFactory): ViewModelProvider.Factory

}