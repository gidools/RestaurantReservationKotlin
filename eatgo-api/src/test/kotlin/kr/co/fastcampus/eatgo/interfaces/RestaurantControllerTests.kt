package kr.co.fastcampus.eatgo.interfaces

import kr.co.fastcampus.eatgo.application.RestaurantService
import kr.co.fastcampus.eatgo.domain.MenuItemRepository
import kr.co.fastcampus.eatgo.domain.MenuItemRepositoryImpl
import kr.co.fastcampus.eatgo.domain.RestaurantRepository
import kr.co.fastcampus.eatgo.domain.RestaurantRepositoryImpl
import org.hamcrest.CoreMatchers.containsString
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.SpyBean
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@ExtendWith(SpringExtension::class)
@WebMvcTest(RestaurantController::class)
class RestaurantControllerTests {

    @Autowired
    private lateinit var mvc: MockMvc

    @SpyBean(RestaurantService::class)
    private lateinit var restaurantService: RestaurantService

    @SpyBean(MenuItemRepositoryImpl::class)
    private lateinit var menuItemRepository: MenuItemRepository

    @SpyBean(RestaurantRepositoryImpl::class)
    private lateinit var restaurantRepository: RestaurantRepository

    @Test
    fun list() {
        mvc.perform(get(RestaurantController.GET_RESTAURANTS))
                .andExpect(status().isOk)
                .andExpect(content().string(
                        containsString("\"id\":1004")
                ))
                .andExpect(content().string(
                        containsString("\"name\":\"Bob zip\"")
                ))
    }

    @Test
    internal fun detail() {
        mvc.perform(get(RestaurantController.GET_RESTAURANTS + "/1004"))
                .andExpect(status().isOk)
                .andExpect(content().string(
                        containsString("\"id\":1004")
                ))
                .andExpect(content().string(
                        containsString("\"name\":\"Bob zip\"")
                ))
                .andExpect(content().string(
                        containsString("Kimchi")
                ))

        mvc.perform(get(RestaurantController.GET_RESTAURANTS + "/2020"))
                .andExpect(status().isOk)
                .andExpect(content().string(
                        containsString("\"id\":2020")
                ))
                .andExpect(content().string(
                        containsString("\"name\":\"Cyber food\"")
                ))
    }
}