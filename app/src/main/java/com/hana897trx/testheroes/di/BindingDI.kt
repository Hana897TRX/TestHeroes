package com.hana897trx.testheroes.di

import com.hana897trx.testheroes.data.remote.heroes.HeroesRemoteDS
import com.hana897trx.testheroes.data.remote.heroes.HeroesRemoteDSImp
import com.hana897trx.testheroes.repository.horoes.HeroesRepository
import com.hana897trx.testheroes.repository.horoes.HeroesRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class BindingDI {
    @Binds
    abstract fun getHeroesRemoteDS(
        impl: HeroesRemoteDSImp
    ) : HeroesRemoteDS

    @Binds
    abstract fun getHeroesRepository(
        imp: HeroesRepositoryImp
    ) : HeroesRepository
}