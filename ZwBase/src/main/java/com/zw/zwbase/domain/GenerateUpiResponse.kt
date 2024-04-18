package com.zw.zwbase.domain

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by Janak on 29/03/24.
 */
@JsonClass(generateAdapter = true)
data class GenerateUpiResponse(
	@Json(name = "responseCode") val responseCode: Int,
	@Json(name = "success") val success: Boolean? = false,
	@Json(name = "message") val message: String? = null,
	@Json(name = "data") val data: UpiData? = null)

data class UpiData(@Json(name = "qr") val qr: String?,
				   @Json(name = "walletTransactionId") val walletTransactionId: String?,
				   @Json(name = "userTrasnactionId") val userTrasnactionId: String?,
				   @Json(name = "status") val status: String?,
				   @Json(name = "statusMessage") val statusMessage: String?,
				   @Json(name = "statusCode") val statusCode: String?,)