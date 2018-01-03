package com.abhi.androidarchitecturecomponents.data.local.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.abhi.androidarchitecturecomponents.data.local.dao.FakePostDao
import com.abhi.androidarchitecturecomponents.data.local.entity.FakePost

/**
 * @author Abhishek Prajapati
 * @version 1.0.0
 * @since 1/2/18.
 */

@Database(entities = arrayOf(FakePost::class), version = 1, exportSchema = false)
abstract class FakeDatabase: RoomDatabase() {
    abstract fun fakePostDao() : FakePostDao
}