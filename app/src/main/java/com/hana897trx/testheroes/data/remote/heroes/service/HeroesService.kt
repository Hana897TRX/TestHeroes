package com.hana897trx.testheroes.data.remote.heroes.service

import com.hana897trx.testheroes.data.remote.heroes.model.HeroResponse
import com.hana897trx.testheroes.data.remote.heroes.model.HeroesResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface HeroesService {
    @GET("search/all")
    suspend fun getAllHeroes() : HeroesResponse

    @GET("{id}")
    suspend fun getHeroe(
        @Path("id") id: Long
    ) : HeroResponse
}