package com.zw.zwbase.domain

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by Janak on 29/03/24.
 */
@JsonClass(generateAdapter = true)
data class DoTransactionResponse(
	@Json(name = "responseCode") val responseCode: Int,
	@Json(name = "success") val success: Boolean? = false,
	@Json(name = "message") val message: String? = null,
	@Json(name = "data") val data: DoTransactionData? = null)

data class DoTransactionData(@Json(name = "rrn") val rrn: String?,
				   @Json(name = "status") val status: String?,
				   @Json(name = "message") val message: String?,
				   @Json(name = "apiWalletTransactionId") val apiWalletTransactionId: String?,
				   @Json(name = "apiTransactionId") val apiTransactionId: String?,
				   @Json(name = "statusCode") val statusCode: String?,)