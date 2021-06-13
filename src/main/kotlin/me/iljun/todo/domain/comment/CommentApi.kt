package me.iljun.todo.domain.comment

import org.springframework.web.bind.annotation.RestController

@RestController
class CommentApi(
    val commentService: CommentService) {
}
