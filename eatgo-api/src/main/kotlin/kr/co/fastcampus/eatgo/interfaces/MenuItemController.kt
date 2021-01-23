package kr.co.fastcampus.eatgo.interfaces

import kr.co.fastcampus.eatgo.application.MenuItemService
import kr.co.fastcampus.eatgo.domain.MenuItem
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class MenuItemController {

    @Autowired
    private lateinit var menuItemService: MenuItemService

    @PatchMapping(API_MENU_ITEMS)
    fun bulkUpdate(@PathVariable("restaurantId") restaurantId: Long,
                   @RequestBody menuItems: List<MenuItem>): String {
        return menuItemService.bulkUpdate(restaurantId, menuItems)
    }

    companion object {
        const val API_MENU_ITEMS = "/restaurants/{restaurantId}/menuitems"
    }
}