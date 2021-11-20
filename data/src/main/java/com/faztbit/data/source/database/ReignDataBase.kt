package com.faztbit.data.source.database

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.faztbit.data.source.database.dao.HitsDao
import com.faztbit.data.source.database.dao.HitsRemovedDao
import com.faztbit.data.source.database.model.HitsDb
import com.faztbit.data.source.database.model.HitsRemovedDb

@Database(entities = [HitsDb::class, HitsRemovedDb::class], version = 1)
abstract class ReignDataBase : RoomDatabase() {

    abstract fun hitsDao(): HitsDao
    abstract fun hitsRemovedDao(): HitsRemovedDao

}

object ReignDataBaseObject {
    fun provideLocalDataBase(
        applicationContext: Application,
    ): ReignDataBase {
        return Room.databaseBuilder(applicationContext, ReignDataBase::class.java, "BaseDb")
            .fallbackToDestructiveMigration()
            //.addMigrations(*migrations.toTypedArray())
            .build()
    }

    fun provideStatsManagementDao(mistiDataBase: ReignDataBase): HitsDao {
        return mistiDataBase.hitsDao()
    }

    fun provideScheduledVisitDao(mistiDataBase: ReignDataBase): HitsRemovedDao {
        return mistiDataBase.hitsRemovedDao()
    }
}