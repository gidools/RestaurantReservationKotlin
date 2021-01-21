package kr.co.fastcampus.eatgo.interfaces

import org.hamcrest.CoreMatchers.containsString
import org.json.JSONObject
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
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

    @Test
    fun list() {
        mvc.perform(get(RestaurantController.GET_RESTAURANTS))
                .andExpect(status().isOk)
                .andExpect(content().string(
                        containsString("\"name\":\"Bob zip\"")
                ))
    }

}