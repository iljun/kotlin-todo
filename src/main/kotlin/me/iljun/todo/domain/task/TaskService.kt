package me.iljun.todo.domain.task

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class TaskService (
    val taskRepository: TaskRepository){

    fun selectTasks(pageAble: Pageable): Page<Task> {
        return taskRepository.findAll(pageAble)
    }

    fun selectTask(id: Long): Task {
        return taskRepository.findById(id)
            .orElseThrow{
                TaskException()
            }
    }

    fun createTask(taskCreateDto: TaskCreateDto): Task {
        val task = taskCreateDto.toEntity()
        return taskRepository.save(task)
    }

    fun updateTask(
        taskId: Long,
        taskUpdateDto: TaskUpdateDto): Task {
        val task = taskRepository.findById(taskId)
            .orElseThrow{
                TaskException()
            }

        val updateTask = taskUpdateDto.toEntity(task)
        return taskRepository.save(updateTask)
    }

    fun deleteTask(taskId: Long) {
        val task = taskRepository.findById(taskId)
            .orElseThrow{
                TaskException()
            }

        taskRepository.delete(task)
    }
}
