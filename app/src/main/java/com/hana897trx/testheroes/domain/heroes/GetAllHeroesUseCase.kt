package com.hana897trx.testheroes.domain.heroes

import com.hana897trx.testheroes.repository.horoes.HeroesRepository
import com.hana897trx.testheroes.utils.DataSource
import com.hana897trx.testheroes.utils.DataState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetAllHeroesUseCase @Inject constructor(
    private val heroesRepository: HeroesRepository
) {
    operator fun invoke() = flow {
        emit(DataState.Loading)
        try {
            emit(DataState.Success(heroesRepository.getAllHeroes(DataSource.Remote)))
        } catch (e: Exception) {
            emit(DataState.Error(code = 0, errorMessage = e.message.orEmpty()))
        }
    }.flowOn(Dispatchers.IO)
}