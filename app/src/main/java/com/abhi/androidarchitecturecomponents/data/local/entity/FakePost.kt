package com.abhi.androidarchitecturecomponents.data.local.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcel
import android.os.Parcelable
import com.abhi.androidarchitecturecomponents.util.createParcel
import com.google.gson.annotations.SerializedName

/**
 * @author Abhishek Prajapati
 * @version 1.0.0
 * @since 1/2/18.
 *
 * model class represents the model for the post data in the database
 * This class is responsible for creating a table name "posts" in the database
 * It has four columns: "id", "title", "body", "userID"
 */
@Entity(tableName = "posts")
data class FakePost(

	@PrimaryKey
	@field:SerializedName("id")
	var id: Int? = null,

	@field:SerializedName("title")
	var title: String? = null,

	@field:SerializedName("body")
	var body: String? = null,

	@field:SerializedName("userId")
	var userId: Int? = null
) : Parcelable {

	companion object {
		@JvmField @Suppress("unused")
		val CREATOR = createParcel { FakePost(it) }
	}

	private constructor(parcelIn: Parcel) : this (
		parcelIn.readInt(),
		parcelIn.readString(),
		parcelIn.readString(),
		parcelIn.readInt()
	)

	override fun writeToParcel(dest: Parcel?, flags: Int) {
		id?.let { dest?.writeInt(it) }
		title?.let { dest?.writeString(it) }
		body?.let { dest?.writeString(it) }
		userId?.let { dest?.writeInt(it) }
	}

	override fun describeContents(): Int = 0


}