package me.iljun.todo.domain.task

import java.time.LocalDateTime
import javax.persistence.*

@Entity(name = "task")
@Table(name = "task")
class Task(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long? = null,
    var title: String,
    var content: String,
    var startDate: LocalDateTime? = null,
    var endDate: LocalDateTime? = null
) {
}
