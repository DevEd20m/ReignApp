package com.faztbit.data.source.database.dataSource

import android.database.sqlite.SQLiteException
import com.faztbit.data.source.database.dao.HitsDao
import com.faztbit.data.source.database.dao.HitsRemovedDao
import com.faztbit.data.source.database.model.HitsDb
import com.faztbit.data.source.database.model.HitsRemovedDb
import com.faztbit.domain.utils.Either

import com.faztbit.domain.utils.Failure

class MainDataBaseDataSourceImpl(
    private val hitsDao: HitsDao,
    private val hitsRemovedDao: HitsRemovedDao
) :
    MainDataBaseDataSource {
    override suspend fun getHitsSaved(): Either<Failure, List<HitsDb>> {
        return try {
            Either.Success(hitsDao.fetchHitsSaved())
        } catch (exception: SQLiteException) {
            Either.Error(Failure.DataBaseError(exception.message))
        }
    }

    override suspend fun saveHits(data: List<HitsDb>): Either<Failure, Unit> {
        return try {
            Either.Success(hitsDao.registerAllHits(data))
        } catch (exception: SQLiteException) {
            Either.Error(Failure.DataBaseError(exception.message))
        }
    }

    override suspend fun saveHitsRemoved(data: HitsRemovedDb): Either<Failure, Unit> {
        return try {
            Either.Success(hitsRemovedDao.registerHitsRemoved(data))
        } catch (exception: SQLiteException) {
            Either.Error(Failure.DataBaseError(exception.message))
        }
    }

    override suspend fun getHitsRemoved(): Either<Failure, List<HitsRemovedDb>> {
        return try {
            Either.Success(hitsRemovedDao.getHitsRemoved())
        } catch (exception: SQLiteException) {
            Either.Error(Failure.DataBaseError(exception.message))
        }
    }
}