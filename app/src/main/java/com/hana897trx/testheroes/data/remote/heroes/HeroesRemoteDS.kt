package com.hana897trx.testheroes.data.remote.heroes

import com.hana897trx.testheroes.data.remote.heroes.model.HeroResponse
import com.hana897trx.testheroes.data.remote.heroes.model.HeroesResponse

interface HeroesRemoteDS {
    suspend fun getHeroes() : HeroesResponse
    suspend fun getHero(id: Long) : HeroResponse
}