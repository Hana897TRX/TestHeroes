package com.hana897trx.testheroes.repository.horoes

import com.hana897trx.testheroes.data.remote.heroes.model.HeroResponse
import com.hana897trx.testheroes.data.remote.heroes.model.HeroesResponse
import com.hana897trx.testheroes.utils.DataSource

interface HeroesRepository {
    suspend fun getAllHeroes(dataSource: DataSource) : HeroesResponse
    suspend fun getHeroe(id: Long) : HeroResponse
}