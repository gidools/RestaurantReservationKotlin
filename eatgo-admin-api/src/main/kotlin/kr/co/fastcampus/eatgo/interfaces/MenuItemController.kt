package kr.co.fastcampus.eatgo.interfaces

import kr.co.fastcampus.eatgo.application.MenuItemService
import kr.co.fastcampus.eatgo.domain.MenuItem
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
class MenuItemController {

    @Autowired
    private lateinit var menuItemService: MenuItemService

    @PatchMapping(API_MENU_ITEMS)
    fun bulkUpdate(@PathVariable("restaurantId") restaurantId: Long,
                   @RequestBody menuItems: List<MenuItem>): String {
        return menuItemService.bulkUpdate(restaurantId, menuItems)
    }

    @GetMapping(API_MENU_ITEMS)
    fun list(@PathVariable("restaurantId") restaurantId: Long): List<MenuItem> {
        return menuItemService.getMenuItems(restaurantId)
    }

    companion object {
        const val API_MENU_ITEMS = "/restaurants/{restaurantId}/menuitems"
    }
}