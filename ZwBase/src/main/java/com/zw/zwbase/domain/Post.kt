package com.zw.zwbase.domain

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

/**
 * Created by Janak on 17/04/24.
 */
@Parcelize
@JsonClass(generateAdapter = true)
data class Post(@Json(name = "id") val id: Int, @Json(name = "userId") val userId: Int,@Json(name = "title") val title: String,@Json(name = "body") var body: String) : Parcelable
