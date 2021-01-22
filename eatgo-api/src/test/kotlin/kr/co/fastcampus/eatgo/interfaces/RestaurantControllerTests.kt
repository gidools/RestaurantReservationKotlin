package kr.co.fastcampus.eatgo.interfaces

import kr.co.fastcampus.eatgo.application.RestaurantService
import kr.co.fastcampus.eatgo.domain.MenuItem
import kr.co.fastcampus.eatgo.domain.Restaurant
import org.hamcrest.CoreMatchers.containsString
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.verify
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@ExtendWith(SpringExtension::class)
@WebMvcTest(RestaurantController::class)
class RestaurantControllerTests {

    @Autowired
    private lateinit var mvc: MockMvc

    @MockBean
    private lateinit var restaurantService: RestaurantService

    @Test
    fun list() {
        val restaurants = getRestaurants()
        given(restaurantService.getRestaurants()).willReturn(restaurants)

        mvc.perform(get(RestaurantController.API_RESTAURANTS))
                .andExpect(status().isOk)
                .andExpect(content().string(
                        containsString("\"id\":1004")
                ))
                .andExpect(content().string(
                        containsString("\"name\":\"Bob zip\"")
                ))
    }

    @Test
    fun detail() {
        val restaurant1 = getRestaurant(1004L)
        given(restaurantService.getRestaurant(1004L)).willReturn(restaurant1)

        val restaurant2 = getRestaurant(2020)
        given(restaurantService.getRestaurant(2020)).willReturn(restaurant2)

        mvc.perform(get(RestaurantController.API_RESTAURANTS + "/1004"))
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

        mvc.perform(get(RestaurantController.API_RESTAURANTS + "/2020"))
                .andExpect(status().isOk)
                .andExpect(content().string(
                        containsString("\"id\":2020")
                ))
                .andExpect(content().string(
                        containsString("\"name\":\"Cyber food\"")
                ))
    }

    @Test
    fun create() {
        val restaurant = Restaurant(1234, "Beryong", "Seoul")
//        val created = restaurantService.addRestaurant(restaurant)

        mvc.perform(post(RestaurantController.API_RESTAURANTS)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Beryong\",\"address\":\"Seoul\"}"))
                .andExpect(status().isCreated)
                .andExpect(header().string("location", "/restaurants/1234"))
                .andExpect(content().string("{}"))

        Mockito.verify(restaurantService).addRestaurant(restaurant)
    }

    private fun getRestaurants(): List<Restaurant> {
        return listOf(
                Restaurant(1004, "Bob zip", "Seoul"),
                Restaurant(2020, "Cyber food", "Seoul"),
                Restaurant(2021, "Joker house", "Busan")
        )
    }

    private fun getRestaurant(id: Long): Restaurant {
        val restaurants = getRestaurants()
        val restaurant = restaurants.first { it.id == id }
        restaurant.setMenuItems(listOf(MenuItem("Kimchi")))
        return restaurant
    }

}