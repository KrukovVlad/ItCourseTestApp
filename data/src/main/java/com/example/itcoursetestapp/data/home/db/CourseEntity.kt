package com.example.itcoursetestapp.data.home.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.itcoursetestapp.domain.home.Course

@Entity(tableName = "courses")
data class CourseEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val text: String,
    val price: String,
    val rate: String,
    val startDate: String,
    val hasLike: Boolean,
    val publishDate: String
) {
    fun toDomain() = Course(
        id = id,
        title = title,
        text = text,
        price = price,
        rate = rate,
        startDate = startDate,
        hasLike = hasLike,
        publishDate = publishDate
    )

    fun isDataEqual(other: CourseEntity): Boolean {
        return this.title == other.title &&
                this.text == other.text &&
                this.price == other.price &&
                this.rate == other.rate &&
                this.startDate == other.startDate &&
                this.publishDate == other.publishDate
    }
}