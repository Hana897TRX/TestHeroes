package com.hana897trx.testheroes.data.remote.heroes.model

data class WorkModel(
    val occupation: String = String(),
    val base: String = String()
)

fun WorkModel.getMapState(): String {
    return "Occupation: $occupation\nBase: $base"
}