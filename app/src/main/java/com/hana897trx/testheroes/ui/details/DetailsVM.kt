package com.hana897trx.testheroes.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hana897trx.testheroes.data.remote.heroes.model.HeroModel
import com.hana897trx.testheroes.data.remote.heroes.model.HeroResponse
import com.hana897trx.testheroes.domain.heroes.GetHeroDetailsUseCase
import com.hana897trx.testheroes.utils.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class DetailsVM @Inject constructor(
    private val getHeroDetailsUseCase: GetHeroDetailsUseCase
): ViewModel() {
    private var _detailsState: MutableStateFlow<DataState<HeroResponse>> = MutableStateFlow(DataState.Loading)
    val detailsState: StateFlow<DataState<HeroResponse>> = _detailsState

    fun getHeroDetails(id: Long) {
        getHeroDetailsUseCase(id).onEach { response ->
            _detailsState.emit(response)
        }.launchIn(viewModelScope)
    }
}