package com.faztbit.data.source.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "HitsRemoved")
data class HitsRemovedDb(
    @PrimaryKey
    @ColumnInfo(name = "objectId")
    val objectId: String,
    @ColumnInfo(name = "title")
    val title: String?,
    @ColumnInfo(name = "author")
    val author: String?,
    @ColumnInfo(name = "createAt")
    val createAt: String?,
    @ColumnInfo(name = "url")
    val url: String?
)
