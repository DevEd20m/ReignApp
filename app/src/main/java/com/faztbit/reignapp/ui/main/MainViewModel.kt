package com.faztbit.reignapp.ui.main

import android.util.Log
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

    private val _listRemoved = MutableLiveData<List<HitsDomain>>()
    val listRemoved: LiveData<List<HitsDomain>> = _listRemoved

    private val _listHits = MutableLiveData<List<HitsDomain>>()
    val listHits: LiveData<List<HitsDomain>> = _listHits

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
            _listHits.value = data.filter { it.objectId !in listNotNull.map { item -> item.objectId } }
        }

/*        val listFiltered: List<HitsDomain> =
            data.toSet().minus(listRemoved?.toSet()).toList() as List<HitsDomain>*/

    }


    private fun handleUseCaseFailureFromBase(failure: Failure) {
        when (failure) {
            is Failure.DataBaseError -> Log.e("EDMUNDO", failure.message.toString())
            is Failure.DataToDomainMapperFailure -> Log.e("EDMUNDO", failure.toString())
            is Failure.DomainToPresentationMapperFailure -> Log.e("EDMUNDO", failure.toString())
            is Failure.ErrorNotMapped -> Log.e("EDMUNDO", failure.toString())
            is Failure.ErrorServerNotMapped -> Log.e("EDMUNDO", failure.message.toString())
            Failure.NetworkConnectionLostSuddenly -> Log.e("EDMUNDO", failure.toString())
            Failure.NoNetworkDetected -> Log.e("EDMUNDO", failure.toString())
            is Failure.ResourceNotFound -> Log.e("EDMUNDO", failure.toString())
            Failure.SSLError -> Log.e("EDMUNDO", failure.toString())
            is Failure.ServerError -> Log.e("EDMUNDO", failure.message.toString())
            is Failure.ServiceUncaughtFailure -> Log.e("EDMUNDO", failure.toString())
            Failure.TimeOut -> Log.e("EDMUNDO", failure.toString())
        }
    }
}