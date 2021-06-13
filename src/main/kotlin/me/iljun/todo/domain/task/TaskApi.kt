package me.iljun.todo.domain.task

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
class TaskApi(
    val taskService: TaskService
) {

    @GetMapping("/api/tasks")
    fun findAll(pageAble: Pageable): ResponseEntity<Page<Task>> {
        return ResponseEntity.ok(taskService.selectTasks(pageAble))
    }

    @GetMapping("/api/task/{id}")
    fun findTask(@PathVariable("id") id: Long): ResponseEntity<Task> {
        return ResponseEntity.ok(taskService.selectTask(id))
    }

    @PostMapping("/api/task")
    fun createTask(@RequestBody taskCreateDto: TaskCreateDto): ResponseEntity<Task> {
        val task = taskService.createTask(taskCreateDto)
        return ResponseEntity.created(URI.create("/api/task/${task.id}"))
            .body(task)
    }

    @PatchMapping("/api/task/{id}")
    fun updateTask(@PathVariable("id") id: Long,
                   @RequestBody taskUpdateDto: TaskUpdateDto): ResponseEntity<Task> {
        val task = taskService.updateTask(id, taskUpdateDto)
        return ResponseEntity.noContent()
            .location(URI.create("/api/task/${task.id}"))
            .build()
    }

    @DeleteMapping("/api/task/{id}")
    fun deleteTask(@PathVariable("id") id: Long): ResponseEntity<Unit> {
        taskService.deleteTask(id)
        return ResponseEntity.noContent()
            .location(URI.create("/api/tasks"))
            .build()
    }
}
