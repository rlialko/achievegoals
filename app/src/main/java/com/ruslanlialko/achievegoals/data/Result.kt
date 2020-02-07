package com.ruslanlialko.achievegoals.data


sealed class Result<out R> {

    data class Success<out T>(val data: T) : Result<T>()

    data class Error<out T>(val exception: Exception, val data: T? = null) : Result<T>()
}