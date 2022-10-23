package com.hana897trx.testheroes.domain.heroes

import com.hana897trx.testheroes.data.remote.heroes.model.HeroResponse
import com.hana897trx.testheroes.repository.horoes.HeroesRepository
import com.hana897trx.testheroes.utils.DataState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetHeroDetailsUseCase @Inject constructor(
    private val heroesRepository: HeroesRepository
) {
    operator fun invoke(id: Long) : Flow<DataState<HeroResponse>> = flow {
        emit(DataState.Loading)
        try {
            val data = heroesRepository.getHeroe(id)
            emit(DataState.Success(
                data
            ))
        } catch (e: Exception) {
            emit(DataState.Error(errorMessage = e.message.orEmpty()))
        }
    }.flowOn(Dispatchers.IO)
}