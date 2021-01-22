package kr.co.fastcampus.eatgo.domain

import org.springframework.stereotype.Component

@Component
class MenuItemRepositoryImpl : MenuItemRepository {

    private val menuItems = arrayListOf<MenuItem>()

    init {
        menuItems.add(MenuItem("Kimchi"))
    }

    override fun findAllByRestaurantId(id: Long): List<MenuItem> {
        return menuItems
    }

}
