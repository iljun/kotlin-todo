package me.iljun.todo.domain.comment

import javax.persistence.Embeddable
import javax.persistence.OneToMany

@Embeddable
class Comments {
    @OneToMany
    var comments:Collection<Comment> = emptyList()

    constructor() {
        this.comments = emptyList<Comment>()
    }

    constructor(comments: MutableList<Comment>) {
        this.comments = comments
    }

    fun addComment(comment: Comment) {
        this.comments.plus(comment)
    }
}
