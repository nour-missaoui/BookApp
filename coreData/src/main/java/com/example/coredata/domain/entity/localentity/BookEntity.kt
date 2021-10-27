package com.example.core.domain.entity.localentity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "BOOK_TABLE")
data class BookEntity(
    @PrimaryKey
    @ColumnInfo(name = "idx")
    var id : String,
    @ColumnInfo(name = "title")
    var title : String
):Serializable
