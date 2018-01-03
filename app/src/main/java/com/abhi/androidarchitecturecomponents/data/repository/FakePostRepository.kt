package com.abhi.androidarchitecturecomponents.data.repository

import com.abhi.androidarchitecturecomponents.data.local.dao.FakePostDao
import com.abhi.androidarchitecturecomponents.data.local.entity.FakePost
import com.abhi.androidarchitecturecomponents.data.remote.FakeApiService
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * @author Abhishek Prajapati
 * @version 1.0.0
 * @since 1/2/18.
 */

class FakePostRepository @Inject constructor(private val fakeApiService: FakeApiService, private val fakePostDao: FakePostDao) {

    fun getFakePosts() : Single<List<FakePost>> {
        return fakeApiService.getFakePosts().onErrorResumeNext {
            fakePostDao.getPostsFromDB()
        }.doOnSuccess {
            fakePostDao.insertFakePosts(it)
        }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}