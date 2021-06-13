package me.iljun.todo.domain.task

import org.springframework.web.bind.annotation.RestController

@RestController
class TaskApi(
    val taskService: TaskService
) {
}
