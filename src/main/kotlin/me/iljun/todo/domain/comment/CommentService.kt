package me.iljun.todo.domain.comment

import org.springframework.stereotype.Service

@Service
class CommentService(
    val commentRepository: CommentRepository) {
}
