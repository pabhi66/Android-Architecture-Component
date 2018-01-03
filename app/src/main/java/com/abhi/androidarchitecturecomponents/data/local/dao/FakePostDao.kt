package com.abhi.androidarchitecturecomponents.data.local.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.abhi.androidarchitecturecomponents.data.local.entity.FakePost
import io.reactivex.Single

/**
 * @author Abhishek Prajapati
 * @version 1.0.0
 * @since 1/2/18.
 */

@Dao
abstract class FakePostDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertFakePosts(postsList: List<FakePost>)

    @Query("SELECT * FROM posts")
    abstract fun getPostsFromDB(): Single<List<FakePost>>

}