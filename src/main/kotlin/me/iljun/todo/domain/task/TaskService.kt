package me.iljun.todo.domain.task

import org.springframework.stereotype.Service

@Service
class TaskService (
    val taskRepository: TaskRepository){
}
