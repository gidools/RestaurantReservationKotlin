package kr.co.fastcampus.eatgo.application

import kr.co.fastcampus.eatgo.domain.MenuItem
import kr.co.fastcampus.eatgo.domain.MenuItemRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class MenuItemService(
        @Autowired
        private val menuItemRepository: MenuItemRepository
) {

    fun bulkUpdate(restaurantId: Long, menuItems: List<MenuItem>): String {
        for (item in menuItems) {
            item.restaurantId = restaurantId
            menuItemRepository.save(item)
        }

        return ""
    }

}
