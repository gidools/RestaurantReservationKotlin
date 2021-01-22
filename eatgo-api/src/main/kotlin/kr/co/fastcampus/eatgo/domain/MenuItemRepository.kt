package kr.co.fastcampus.eatgo.domain

interface MenuItemRepository {
    fun findAllByRestaurantId(id: Long): List<MenuItem>
}