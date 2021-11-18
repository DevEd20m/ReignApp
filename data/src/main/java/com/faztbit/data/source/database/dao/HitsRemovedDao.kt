package com.faztbit.data.source.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.faztbit.data.source.database.model.HitsRemovedDb

@Dao
interface HitsRemovedDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun registerHitsRemoved(data: HitsRemovedDb)

    @Query("SELECT * FROM HitsRemoved")
    suspend fun getHitsRemoved(): List<HitsRemovedDb>

}