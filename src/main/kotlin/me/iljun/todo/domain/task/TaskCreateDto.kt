package me.iljun.todo.domain.task

import java.time.LocalDateTime

class TaskCreateDto(
    val title: String,
    val content: String,
    val startDate: LocalDateTime? = null,
    val endDate: LocalDateTime? = null
) {

    fun toEntity() : Task {
        return Task(
            title = title,
            content = content,
            startDate = startDate,
            endDate =  endDate
        )
    }
}
