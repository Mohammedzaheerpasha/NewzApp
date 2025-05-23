package uk.ac.tees.mad.e4294395.newsapp.data.roomdatabase.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "article")
data class ArticleEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "article_id")
    val id: Int = 0,
    @ColumnInfo(name = "title")
    val title: String = "",
    @ColumnInfo(name = "description")
    val description: String?,
    @ColumnInfo(name = "url")
    val url: String = "",
    @ColumnInfo(name = "urlToImage")
    val imageUrl: String? = "",
    @Embedded val source: SourceEntity
)