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
import com.faztbit.reignapp.utils.Constant
import com.faztbit.reignapp.utils.Event

class MainViewModel(
    private val fetchHitsRemovedUseCase: FetchHitsRemovedUseCase,
    private val fetchHitsUseCase: FetchHitsUseCase,
    private val removedUseCase: RemoveHitsUseCase,
) : ViewModel() {

    private val _listRemoved = MutableLiveData<List<HitsDomain>>()
    val listRemoved: LiveData<List<HitsDomain>> = _listRemoved

    private val _listHits = MutableLiveData<List<HitsDomain>>()
    val listHits: LiveData<List<HitsDomain>> = _listHits

    val messageError = MutableLiveData<Event<String>>()

    val isRefreshData = MutableLiveData<Boolean>()

    init {
        executeFetchHitsRemovedUseCase()
        executeFetchHitsUseCase()
    }

    fun executeFetchHitsUseCase(isRefresh: Boolean = false) {
        fetchHitsUseCase.invoke(viewModelScope) {
            isRefreshData.value = false
            it.either(::handleUseCaseFailureFromBase) { data ->
                handleFetchHitsUseCaseSuccess(data)
            }
        }
    }


    private fun executeFetchHitsRemovedUseCase() {
        fetchHitsRemovedUseCase.invoke(viewModelScope) {
            it.either(::handleUseCaseFailureFromBase) { data ->
                handleFetchHitsRemovedUseCaseSuccess(data)
            }
        }
    }

    private fun handleFetchHitsRemovedUseCaseSuccess(data: List<HitsDomain>) {
        _listRemoved.value = data
    }


    fun deleteHits(hit: HitsDomain) {
        removedUseCase.invoke(viewModelScope, hit) {
            it.either(::handleUseCaseFailureFromBase) { data ->
                handleRemoveHitsUseCaseSuccess(data)
            }
        }
    }

    private fun handleRemoveHitsUseCaseSuccess(data: Unit) {
        executeFetchHitsRemovedUseCase()
        executeFetchHitsUseCase()

    }

    private fun handleFetchHitsUseCaseSuccess(data: List<HitsDomain>) {
        isRefreshData.value = false
        val listRemoved = _listRemoved.value?.toList()
        listRemoved?.let { listNotNull ->
            _listHits.value =
                data.filter { it.objectId !in listNotNull.map { item -> item.objectId } }
        }

    }


    private fun handleUseCaseFailureFromBase(failure: Failure) {
        when (failure) {
            is Failure.DataBaseError -> showErrorCauseWithMessage(Constant.ERROR_DATABASE)
            is Failure.DataToDomainMapperFailure -> showErrorCauseWithMessage(Constant.ERROR_SSL)
            is Failure.DomainToPresentationMapperFailure -> showErrorCauseWithMessage(Constant.ERROR_MAPPER_DOMAIN)
            is Failure.ErrorNotMapped -> showErrorCauseWithMessage(Constant.ERROR_NOT_MAPPED)
            is Failure.ErrorServerNotMapped -> showErrorCauseWithMessage(Constant.ERROR_NOT_MAPPED)
            Failure.NetworkConnectionLostSuddenly -> showErrorCauseWithMessage(Constant.ERROR_NETWORK_LOW)
            Failure.NoNetworkDetected -> showErrorCauseWithMessage(Constant.ERROR_NOT_NETWORK)
            is Failure.ResourceNotFound -> showErrorCauseWithMessage(Constant.ERROR_NOT_FOUND)
            Failure.SSLError -> showErrorCauseWithMessage(Constant.ERROR_SSL)
            is Failure.ServerError -> showErrorCauseWithMessage(Constant.ERROR_SERVER)
            is Failure.ServiceUncaughtFailure -> showErrorCauseWithMessage(Constant.ERROR_SERVER)
            Failure.TimeOut -> showErrorCauseWithMessage(Constant.ERROR_TIMEOUT)
        }
    }

    protected fun showErrorCauseWithMessage(message: String) {
        messageError.value = Event(message)
    }
}