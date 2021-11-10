package com.example.dubizzletest.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class ListResponse(

	@field:SerializedName("pagination")
	val pagination: Pagination? = null,

	@field:SerializedName("results")
	var results: List<ResultsItem?>? = null
)

@Parcelize
data class ResultsItem(

	@field:SerializedName("uid")
	val uid: String? = null,

	@field:SerializedName("price")
	val price: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("image_ids")
	val imageIds: List<String?>? = null,

	@field:SerializedName("image_urls")
	val imageUrls: List<String?>? = null,

	@field:SerializedName("image_urls_thumbnails")
	val imageUrlsThumbnails: List<String?>? = null
) : Parcelable

data class Pagination(

	@field:SerializedName("key")
	val key: Any? = null
)
