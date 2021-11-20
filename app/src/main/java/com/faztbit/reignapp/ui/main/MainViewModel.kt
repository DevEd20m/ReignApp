package com.faztbit.reignapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.faztbit.domain.models.HitsDomain
import com.faztbit.domain.usecase.FetchHitsRemovedUseCase
import com.faztbit.domain.usecase.FetchHitsUseCase
import com.faztbit.domain.usecase.RemoveHitsUseCase
import com.faztbit.domain.utils.Failure

class MainViewModel(
    private val fetchHitsRemovedUseCase: FetchHitsRemovedUseCase,
    private val fetchHitsUseCase: FetchHitsUseCase,
    private val removedUseCase: RemoveHitsUseCase,

    ) : ViewModel() {

    private val _listHits = MutableLiveData<List<HitsDomain>>()
    val listHits: LiveData<List<HitsDomain>> = _listHits

    val isRefreshData = MutableLiveData<Boolean>()

    init {
        executeGetDetailEnterprise()
    }

    fun executeGetDetailEnterprise(isRefresh: Boolean = false) {
        fetchHitsUseCase.invoke(viewModelScope) {
            isRefreshData.value = false
            it.either(::handleUseCaseFailureFromBase) { data ->
                handleFetchHitsUseCaseSuccess(data)
            }
        }
    }


    fun deleteHits(hit: HitsDomain) {
        removedUseCase.invoke(viewModelScope, hit.objectId) {
            it.either(::handleUseCaseFailureFromBase) { data ->
                handleRemoveHitsUseCaseSuccess(data)
            }
        }
    }

    private fun handleRemoveHitsUseCaseSuccess(data: Unit) {
        executeGetDetailEnterprise()
    }

    private fun handleFetchHitsUseCaseSuccess(data: List<HitsDomain>) {
        isRefreshData.value = false
        _listHits.value = data
    }


    private fun handleUseCaseFailureFromBase(failure: Failure) {
        when (failure) {
            is Failure.DataBaseError -> TODO()
            is Failure.DataToDomainMapperFailure -> TODO()
            is Failure.DomainToPresentationMapperFailure -> TODO()
            is Failure.ErrorNotMapped -> TODO()
            is Failure.ErrorServerNotMapped -> TODO()
            Failure.NetworkConnectionLostSuddenly -> TODO()
            Failure.NoNetworkDetected -> TODO()
            is Failure.ResourceNotFound -> TODO()
            Failure.SSLError -> TODO()
            is Failure.ServerError -> TODO()
            is Failure.ServiceUncaughtFailure -> TODO()
            Failure.TimeOut -> TODO()
        }
    }
}