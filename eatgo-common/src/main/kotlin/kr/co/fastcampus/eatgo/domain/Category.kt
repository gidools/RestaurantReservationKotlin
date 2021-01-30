package kr.co.fastcampus.eatgo.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class Category(
        @Id
        @GeneratedValue
        val id: Long? = null,
        val name: String)