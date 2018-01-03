package com.abhi.androidarchitecturecomponents.di.components

import android.app.Application
import com.abhi.androidarchitecturecomponents.MainApp
import com.abhi.androidarchitecturecomponents.di.modules.ActivityModule
import com.abhi.androidarchitecturecomponents.di.modules.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

/**
 * @author Abhishek Prajapati
 * @version 1.0.0
 * @since 1/2/18.
 *
 * This is the main injection class that injects 'MainApp' Activities, and android modules.
 */
@Singleton
@Component(modules = arrayOf(
        AndroidInjectionModule::class,
        AppModule::class,
        ActivityModule::class
))
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(application: MainApp)
}