package com.hana897trx.testheroes.repository.horoes

import com.hana897trx.testheroes.data.remote.heroes.HeroesRemoteDS
import com.hana897trx.testheroes.data.remote.heroes.model.HeroResponse
import com.hana897trx.testheroes.data.remote.heroes.model.HeroesResponse
import com.hana897trx.testheroes.utils.DataSource
import javax.inject.Inject

class HeroesRepositoryImp @Inject constructor(
    private val heroesRemoteDS: HeroesRemoteDS
): HeroesRepository {
    override suspend fun getAllHeroes(dataSource: DataSource): HeroesResponse {
        return if(dataSource == DataSource.Remote) {
            heroesRemoteDS.getHeroes()
        } else {
            HeroesResponse()
        }
    }

    override suspend fun getHeroe(id: Long): HeroResponse {
        return heroesRemoteDS.getHero(id)
    }
}