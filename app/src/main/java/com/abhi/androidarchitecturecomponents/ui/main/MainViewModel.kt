package com.abhi.androidarchitecturecomponents.ui.main

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.abhi.androidarchitecturecomponents.data.local.entity.FakePost
import com.abhi.androidarchitecturecomponents.data.repository.FakePostRepository
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * @author Abhishek Prajapati
 * @version 1.0.0
 * @since 1/2/18.
 *
 * Main view model class that has all the logic of what the main activity/fragment should do
 * and does not contain any UI views.
 */

class MainViewModel @Inject constructor(private val fakePostRepository: FakePostRepository): ViewModel() {

    var disposable = CompositeDisposable()

    val postLiveList = MutableLiveData<List<FakePost>>()

    val postList: LiveData<List<FakePost>>
        get() = postLiveList

    fun getPostsList(): LiveData<List<FakePost>> {
        disposable.add(fakePostRepository.getFakePosts()
                .subscribe { response -> postLiveList.value = response })

        return postList
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }
}