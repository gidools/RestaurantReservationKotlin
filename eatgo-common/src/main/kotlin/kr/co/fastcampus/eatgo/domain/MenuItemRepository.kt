package kr.co.fastcampus.eatgo.domain

import org.springframework.data.repository.CrudRepository

interface MenuItemRepository: CrudRepository<MenuItem, Long> {
    fun save(menuItem: MenuItem): MenuItem
    fun findAllByRestaurantId(id: Long): List<MenuItem>
    override fun deleteById(id: Long)
}