package com.faztbit.data.source.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Hits")
data class HitsDb(
    @ColumnInfo(name = "objectId")
    @PrimaryKey
    val objectId: String?,
    @ColumnInfo(name = "title")
    val title: String?,
    @ColumnInfo(name = "author")
    val author: String?,
    @ColumnInfo(name = "createAt")
    val createAt: String?
)
