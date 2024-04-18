/*
 * Copyright © 2022 GFF. All rights reserved.
 *
 * Android Compose Template
 *
 * Created by GFF developers.
 */
package com.zw.zwbase.core

import com.skydoves.sandwich.ApiResponse
import com.zw.zwbase.data.response.BaseResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flowOf

fun <T> ApiResponse<T>.toResource(): Resource<T> {
    return when (this) {
        is ApiResponse.Success -> if (this.data is BaseResponse<*>) {
            if ((this.data as BaseResponse<*>).responseCode==200){
                Resource.Success(this.data)
            } else {
               Resource.Failure(Error.FromApi("", (this.data as BaseResponse<*>).responseCode!!,(this.data as BaseResponse<*>).message!!))
            }
        }
        else {
            Resource.Success(this.data)
        }
        is ApiResponse.Failure.Error -> Resource.Failure(this.asError())
        is ApiResponse.Failure.Exception -> Resource.Failure(this.asError())
    }
}

fun <T> ApiResponse<T>.data(): T? {
    return when (this) {
        is ApiResponse.Success -> this.data
        else -> null
    }
}


fun <T> List<ApiResponse<T>>.toResource(): Resource<List<T>> {
    if (this.isEmpty()) return Resource.None()
    val success = this.filterIsInstance<ApiResponse.Success<T>>()
    val error = this.filterIsInstance<ApiResponse.Failure.Error<T>>()
    val exception = this.filterIsInstance<ApiResponse.Failure.Exception<T>>()
    return when {
        success.isNotEmpty() -> success.mapNotNull { it.data }.run { Resource.Success(this) }
        error.isNotEmpty() -> Resource.Failure(error.first().asError())
        exception.isNotEmpty() -> Resource.Failure(exception.first().asError())
        else -> Resource.None()
    }
}


fun <T> Resource<T>.cache(
    save: (T) -> Unit,
    restore: () -> T?,
): Resource<T?> {
    return when (this) {
        is Resource.Failure -> restore()?.run { Resource.Success(this) }
            ?: Resource.Failure(this.error)

        is Resource.Loading -> Resource.Loading()
        is Resource.Success -> Resource.Success(this.data.also { it?.run(save) })
        is Resource.None -> Resource.None()
    }
}


fun <In, Out> Resource<In>.map(block: (In) -> Out?): Resource<Out> {
    return when (this) {
        is Resource.Failure -> Resource.Failure(this.error)
        is Resource.Loading -> Resource.Loading()
        is Resource.Success -> Resource.Success(this.data?.run(block))
        is Resource.None -> Resource.None()
    }
}


fun <In, Out : Any> Resource<In>.mapOrError(error: Error, block: (In) -> Out): Resource<Out> {
    return when (this) {
        is Resource.Failure -> Resource.Failure(this.error)
        is Resource.Loading -> Resource.Loading()
        is Resource.Success -> Resource.Success(
            this.data?.run(block) ?: return Resource.None()
        )

        is Resource.None -> Resource.None()
    }
}

fun <T> Resource<List<T>>.filter(block: (T) -> Boolean): Resource<List<T>> {
    return when (this) {
        is Resource.Failure -> Resource.Failure(this.error)
        is Resource.Loading -> Resource.Loading()
        is Resource.Success -> Resource.Success(this.data?.filter(block))
        is Resource.None -> Resource.None()
    }
}

fun <In, Out> Resource<List<In>>.applyToList(block: (List<In>) -> List<Out>): Resource<List<Out>> {
    return when (this) {
        is Resource.Failure -> Resource.Failure(this.error)
        is Resource.Loading -> Resource.Loading()
        is Resource.Success -> Resource.Success(this.data?.run(block))
        is Resource.None -> Resource.None()
    }
}

fun <In, Out> Resource<List<In>>.mapIterable(block: (In) -> Out?): Resource<List<Out>> {
    return when (this) {
        is Resource.Failure -> Resource.Failure(this.error)
        is Resource.Loading -> Resource.Loading()
        is Resource.Success -> this.unwrap()?.mapNotNull { block(it) }.success()
        is Resource.None -> Resource.None()
    }
}

fun <In, Out> Resource<In>.mapOrNullOnError(block: (In?) -> Out?): Resource<Out> {
    return when (this) {
        is Resource.Failure -> Resource.Success(block(null))
        is Resource.Loading -> Resource.Loading()
        is Resource.Success -> Resource.Success(this.data?.run(block))
        is Resource.None -> Resource.None()
    }
}

fun Resource<*>.consumeOnSuccess(): EmptyResource {
    return when (this) {
        is Resource.Failure -> Resource.Failure(this.error)
        is Resource.Loading -> Resource.Loading()
        is Resource.Success -> Resource.Success(Unit)
        is Resource.None -> Resource.None()
    }
}

suspend fun <In, Out> Resource<In>.andThen(block: suspend (In) -> Resource<Out>): Resource<Out> {
    return when (this) {
        is Resource.Failure -> Resource.Failure(this.error)
        is Resource.Loading -> Resource.Loading()
        is Resource.Success -> this.data?.run { block(this) } ?: Resource.Success(null)
        is Resource.None -> Resource.None()
    }
}

suspend fun <T> Resource<T>.doOnSuccess(block: suspend (T) -> Unit): Resource<T> {
    if (this is Resource.Success) data?.run { block(this) }
    return this
}

suspend fun <T> Resource<T>.doOnEmptySuccess(block: suspend () -> Unit): Resource<T> {
    if (this is Resource.Success) block()
    return this
}


suspend fun <T> Resource<T>.doOnNext(block: suspend () -> Unit): Resource<T> {
    block()
    return this
}

fun <In, Out> Flow<Resource<In>>.andThen(block: (In) -> Flow<Resource<Out>>): Flow<Resource<Out>> {
    return this.flatMapMerge { resource ->
        when (resource) {
            is Resource.Failure -> flowOf(Resource.Failure(resource.error))
            is Resource.Loading -> flowOf(Resource.Loading())
            is Resource.Success -> resource.data?.run(block) ?: flowOf(Resource.Success(null))
            is Resource.None -> flowOf(Resource.None())
        }
    }
}

suspend fun <In, Out> Resource<List<In>>.andThenIterable(block: suspend (In) -> Resource<Out>): Resource<List<Out>> {
    return when (this) {
        is Resource.Failure -> Resource.Failure(this.error)
        is Resource.Loading -> Resource.Loading()
        is Resource.Success -> this.unwrap()?.mapNotNull { block(it) }?.toResourceList()
            ?: emptyList<Out>().success()

        is Resource.None -> Resource.None()
    }
}

suspend fun <In, Out> Flow<Resource<List<In>>>.andThenIterable(block: suspend (In) -> Resource<Out>): Flow<Resource<List<Out>>> {
    return this.flatMapMerge { resource ->
        when (resource) {
            is Resource.Failure -> flowOf(Resource.Failure(resource.error))
            is Resource.Loading -> flowOf(Resource.Loading())
            is Resource.Success -> flowOf(resource.andThenIterable(block))
            is Resource.None -> flowOf(Resource.None())
        }
    }
}

fun <T> List<Resource<T>>.toResourceList(): Resource<List<T>> {
    val list = mutableListOf<T>()
    this.forEach {
        if (it is Resource.Success && it.data != null) {
            list += it.data
        }
    }
    return list.success()
}

fun <T> Resource<T?>.orEmpty(): Resource<T> = map { it }

fun <T> Resource<T>.toFlow() = flowOf(this)

