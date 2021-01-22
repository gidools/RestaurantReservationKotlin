package kr.co.fastcampus.eatgo.domain

import org.springframework.data.repository.CrudRepository

interface MenuItemRepository: CrudRepository<MenuItem, Long> {
    fun findAllByRestaurantId(id: Long): List<MenuItem>
}