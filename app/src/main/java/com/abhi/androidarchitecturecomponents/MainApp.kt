package com.abhi.androidarchitecturecomponents

import android.app.Activity
import android.app.Application
import com.abhi.androidarchitecturecomponents.di.components.DaggerAppComponent
import com.facebook.stetho.Stetho
import com.squareup.leakcanary.LeakCanary
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

/**
 * @author Abhishek Prajapati
 * @version 1.0.0
 * @since 1/2/18.
 */

class MainApp : Application(), HasActivityInjector {

    @Inject
    lateinit var dispathingActivityInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()

        initInjector()

        if (LeakCanary.isInAnalyzerProcess(this)) return
        LeakCanary.install(this)

        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this)
        }
    }

    override fun activityInjector(): AndroidInjector<Activity> = dispathingActivityInjector

    private fun initInjector() {
        DaggerAppComponent.builder()
                .application(this)
                .build()
                .inject(this)
    }
}