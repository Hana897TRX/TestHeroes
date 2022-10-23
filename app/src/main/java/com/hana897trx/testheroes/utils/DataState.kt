package com.hana897trx.testheroes.utils

sealed class DataState<out T : Any?> {
    data class Success<out T : Any?>(val data : T) : DataState<T>()
    object Loading : DataState<Nothing>()
    data class Error(val code : Int = 0, val errorMessage : String = String()) : DataState<Nothing>()
}