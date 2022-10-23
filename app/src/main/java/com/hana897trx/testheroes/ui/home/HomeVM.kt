package com.hana897trx.testheroes.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hana897trx.testheroes.data.remote.heroes.model.HeroModel
import com.hana897trx.testheroes.data.remote.heroes.model.toHeroesResponse
import com.hana897trx.testheroes.domain.heroes.GetAllHeroesUseCase
import com.hana897trx.testheroes.domain.heroes.GetHeroDetailsUseCase
import com.hana897trx.testheroes.utils.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeVM @Inject constructor(
    private val getAllHeroesUseCase: GetAllHeroesUseCase,
    private val getHeroUseCase: GetHeroDetailsUseCase
): ViewModel() {
    private var _allHeroesState: MutableStateFlow<DataState<List<HeroModel>>> = MutableStateFlow(DataState.Loading)
    val allHeroesState: StateFlow<DataState<List<HeroModel>>> = _allHeroesState

    private var index: Long = 5

    init {
        getAllHeroes()
    }

    private fun getAllHeroes() {
        getAllHeroesUseCase().onEach { response ->
            when(response) {
                is DataState.Error -> _allHeroesState.emit(response)
                is DataState.Loading -> _allHeroesState.emit(response)
                is DataState.Success -> _allHeroesState.emit(DataState.Success(response.data.results))
            }
        }.launchIn(viewModelScope)
    }

    fun requestNextFive() {
        for(i in index..index + 5)
        getHeroUseCase(i).onEach { response ->
            when(response) {
                is DataState.Error -> {}
                is DataState.Loading -> {}
                is DataState.Success -> {
                    val data = response.data.toHeroesResponse()
                    if(_allHeroesState.value is DataState.Success) {
                        val list = (_allHeroesState.value as DataState.Success).data.toMutableList()
                        list.add(data.results[0])
                        _allHeroesState.emit(DataState.Success(list))
                    }
                }
            }
            index += 5
        }.launchIn(viewModelScope)
    }
}