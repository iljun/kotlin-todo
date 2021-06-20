package me.iljun.todo.domain.task

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.function.Executable
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDateTime
import java.util.stream.Stream

@SpringBootTest
class TaskServiceTest(@Autowired val taskService: TaskService) {

    @ParameterizedTest
    @MethodSource("provideTaskCreateDto")
    @DisplayName("Task 등록")
    fun `insertTask`(taskCreateDto: TaskCreateDto) {
        val task = taskService.createTask(taskCreateDto)

        assertAll(
            Executable { assertNotNull(task) },
            Executable { assertTrue(task.id!! > 0) },
            Executable { assertEquals(task.content, taskCreateDto.content) },
            Executable { assertEquals(task.title, taskCreateDto.title) },
            Executable { assertEquals(task.startDate, taskCreateDto.startDate) },
            Executable { assertEquals(task.endDate, taskCreateDto.endDate) },
        )
    }

    companion object {
        @JvmStatic
        fun provideTaskCreateDto() = Stream.of(
                Arguments.of(
                    TaskCreateDto(title = "firstTitle", content = "firstContent", null, null),
                    TaskCreateDto(title = "secondTitle", content = "secondContent", startDate = LocalDateTime.now().plusDays(2), null),
                    TaskCreateDto(title = "thirdTitle", content = "thirdContent", null, endDate = LocalDateTime.now().plusDays(5)),
                    TaskCreateDto(title = "fourthTitle", content = "fourthContent", startDate = LocalDateTime.now(), endDate = LocalDateTime.now().plusDays(5))
                )
            )

        @JvmStatic
        fun provideTaskUpdateDto() = Stream.of(
            Arguments.of(
                TaskUpdateDto(title = "firstTitle", content = "firstContent", null, null),
                TaskUpdateDto(title = "secondTitle", content = "secondContent", startDate = LocalDateTime.now().plusDays(2), null),
                TaskUpdateDto(title = "thirdTitle", content = "thirdContent", null, endDate = LocalDateTime.now().plusDays(5)),
                TaskUpdateDto(title = "fourthTitle", content = "fourthContent", startDate = LocalDateTime.now(), endDate = LocalDateTime.now().plusDays(5))
            )
        )
    }

    val targetTask = TaskCreateDto(title = "sample Task", content = "sample Task", startDate = null, endDate = null)

    @ParameterizedTest
    @MethodSource("provideTaskUpdateDto")
    @DisplayName("Task 내용 변경")
    fun `updateTask`(updateDto: TaskUpdateDto) {
        val originTask = taskService.createTask(targetTask)
        val updateTask = taskService.updateTask(originTask.id!!, updateDto)

        assertAll(
            Executable { assertNotNull(updateTask) },
            Executable { assertEquals(updateTask.id, originTask.id) },
            Executable { assertEquals(updateTask.content, updateDto.content) },
            Executable { assertEquals(updateTask.title, updateDto.title) },
            Executable { assertEquals(updateTask.startDate, updateDto.startDate) },
            Executable { assertEquals(updateTask.endDate, updateDto.endDate) },
        )
    }

    @Test
    @DisplayName("존재하지 않는 Task 삭제 시 Exception")
    fun `notFoundTask`() {
        val temporaryTaskId = 999999L
        val updateDto = TaskUpdateDto(title = "firstTitle", content = "firstContent", null, null)

        assertThrows(TaskException::class.java) {
            taskService.updateTask(temporaryTaskId, updateDto)
        }
    }

    @Test
    @DisplayName("Task 삭제")
    fun `deleteTask`() {
        val task = taskService.createTask(targetTask)

        assertDoesNotThrow {
            taskService.deleteTask(task.id!!)
        }
    }

    @Test
    @DisplayName("존재하지 않는 Task 삭제 시 Exception")
    fun `deleteNotFoundTask`() {
        val temporaryTaskId = 9999990L
        assertThrows(TaskException::class.java) {
            taskService.deleteTask(temporaryTaskId)
        }
    }
}
