package me.iljun.todo.domain.task

import java.time.LocalDateTime
import java.util.*

class TaskUpdateDto(
    val title: String? = null,
    val content: String? = null,
    val startDate: LocalDateTime? = null,
    val endDate: LocalDateTime? = null
) {

    fun toEntity(task: Task): Task {
        return Task(
            id = task.id,
            title = Optional.ofNullable(title).orElse(task.title),
            content = Optional.ofNullable(content).orElse(task.content),
            startDate = Optional.ofNullable(startDate).orElse(task.startDate),
            endDate = Optional.ofNullable(endDate).orElse(task.endDate)
        )
    }
}
