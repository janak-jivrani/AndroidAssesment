package com.zw.zwbase.data

import com.skydoves.sandwich.ApiResponse
import com.zw.zwbase.domain.Post
import retrofit2.http.GET

interface AuthApiService {
    @GET("posts")
    @JvmSuppressWildcards
    suspend fun getPosts(): ApiResponse<ArrayList<Post>>

}