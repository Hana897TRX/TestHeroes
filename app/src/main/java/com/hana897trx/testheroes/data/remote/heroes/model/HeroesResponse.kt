package com.hana897trx.testheroes.data.remote.heroes.model

import com.google.gson.annotations.SerializedName

data class HeroesResponse(
    val response: String = String(),
    @SerializedName("results-for")
    val resultsFor: String = String(),
    val results: List<HeroModel> = emptyList()
)
