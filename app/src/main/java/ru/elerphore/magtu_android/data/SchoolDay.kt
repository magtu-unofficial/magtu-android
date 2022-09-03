package ru.elerphore.magtu_android.data

import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class ResponseSchoolDay(val items: List<SchoolDay>)

@Serializable
data class SchoolDay(
    val group: List<String> = Collections.emptyList(),

    val date: kotlinx.datetime.LocalDateTime,

    val pairs: List<Lesson> = Collections.emptyList(),

    val displayName: String,
)

@Serializable
data class Lesson(
    val changes: Boolean? = false,

    val removed: Boolean? = false,

    val error: Boolean? = false,

    val number: Int? = null,

    val subgroup: String? = null,

    val name: String? = null,

    val teacher: String? = null,

    val classroom: String? = null
)