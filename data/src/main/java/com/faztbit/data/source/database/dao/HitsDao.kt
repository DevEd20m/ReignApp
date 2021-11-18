package com.faztbit.data.source.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.faztbit.data.source.database.model.HitsDb

@Dao
interface HitsDao {

    @Query("SELECT * FROM Hits")
    suspend fun fetchHitsSaved(): List<HitsDb>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun registerAllHits(data: List<HitsDb>)

}