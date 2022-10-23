package com.hana897trx.testheroes.data.remote.heroes.model

import com.google.gson.annotations.SerializedName

data class BiographyModel(
    @SerializedName("full-name")
    val fullName: String = String(),
    @SerializedName("alter-egos")
    val alterEgos: String = String(),
    @SerializedName("first-appearance")
    val firstAppearance: String = String()
)

fun BiographyModel.getMapState(): String {
    return "FullName: $fullName\nAlter Egos: $alterEgos\nFirst Appearance: $firstAppearance"
}