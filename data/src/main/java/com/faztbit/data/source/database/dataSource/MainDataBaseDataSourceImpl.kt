package com.faztbit.data.source.database.dataSource

import android.database.sqlite.SQLiteException
import com.faztbit.data.source.database.dao.HitsDao
import com.faztbit.data.source.database.model.HitsDb
import com.faztbit.domain.utils.Either

import com.faztbit.domain.utils.Failure

class MainDataBaseDataSourceImpl(private val dao: HitsDao) : MainDataBaseDataSource {
    override suspend fun getHitsSaved(): Either<Failure, List<HitsDb>> {
        return try {
            Either.Success(dao.fetchHitsSaved())
        } catch (exception: SQLiteException) {
            Either.Error(Failure.DataBaseError(exception.message))
        }
    }

    override suspend fun saveHits(data: List<HitsDb>): Either<Failure, Unit> {
        return try {
            Either.Success(dao.registerAllHits(data))
        } catch (exception: SQLiteException) {
            Either.Error(Failure.DataBaseError(exception.message))
        }
    }
}