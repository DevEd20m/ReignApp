package com.faztbit.data.source.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "HitsRemoved")
data class HitsRemovedDb(
    @PrimaryKey
    @ColumnInfo(name = "Id")
    val id: String
)
