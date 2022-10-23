package com.hana897trx.testheroes.data.remote.heroes

import com.hana897trx.testheroes.data.remote.heroes.model.HeroResponse
import com.hana897trx.testheroes.data.remote.heroes.model.HeroesResponse
import com.hana897trx.testheroes.data.remote.heroes.service.HeroesService
import javax.inject.Inject

class HeroesRemoteDSImp @Inject constructor(
    private val service: HeroesService
): HeroesRemoteDS {
    override suspend fun getHeroes() : HeroesResponse {
        return service.getAllHeroes()
    }

    override suspend fun getHero(id: Long): HeroResponse {
        return service.getHeroe(id)
    }
}