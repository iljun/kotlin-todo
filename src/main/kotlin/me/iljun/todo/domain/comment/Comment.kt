package me.iljun.todo.domain.comment

import javax.persistence.*

@Entity(name = "comment")
@Table(name = "comment")
class Comment (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long? = null,
    var content: String){
}
