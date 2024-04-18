package com.zw.zwbase.data.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class User(
	@Json(name = "partner") val partner: Partner? = null,
	@Json(name = "token") val token: String? = null
)


data class Partner(
	@Json(name = "phone") val phone: String? = null,
	@Json(name = "name") val name: String? = null,
	@Json(name = "id") val id: Int? = null,
	@Json(name = "email") val email: String? = null
)

