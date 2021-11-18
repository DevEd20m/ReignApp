package com.faztbit.data.source.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.faztbit.data.source.database.dao.HitsDao
import com.faztbit.data.source.database.model.HitsDb

@Database(entities = [HitsDb::class], version = 1)
abstract class ReignDataBase : RoomDatabase() {

    abstract fun hitsDao(): HitsDao
}