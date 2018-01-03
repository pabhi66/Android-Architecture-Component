package com.abhi.androidarchitecturecomponents.data.remote

import com.abhi.androidarchitecturecomponents.data.local.entity.FakePost
import io.reactivex.Single
import retrofit2.http.GET

/**
 * @author Abhishek Prajapati
 * @version 1.0.0
 * @since 1/2/18.
 *
 * This is an interface which indicate how a http request will be handled
 */

interface FakeApiService {

    /**
     * get posts with endpoint "/posts" i.e. BASE_URL + "/posts"
     */
    @GET("/posts")
    fun getFakePosts() : Single<List<FakePost>>
}