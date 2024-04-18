package com.zw.zwbase.data.repositories

import com.zw.zwbase.core.toResource
import com.zw.zwbase.data.AuthApiService
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserRepository @Inject constructor(private val api: AuthApiService) {

    suspend fun getPosts() = flow {
        inTryCatch {
            val response = api.getPosts()
            emit(response.toResource())
        }
    }

}
suspend fun inTryCatch(call: suspend () -> Unit) {
    try {
        call()
    } catch (e: Exception) {
        e.printStackTrace()
    }
}