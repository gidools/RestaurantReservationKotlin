package kr.co.fastcampus.eatgo.interfaces

import com.nhaarman.mockito_kotlin.*
import kr.co.fastcampus.eatgo.application.ReviewService
import kr.co.fastcampus.eatgo.domain.Review
import org.hamcrest.CoreMatchers.containsString
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
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
@WebMvcTest(ReviewController::class)
class ReviewControllerTest {

    @MockBean
    private lateinit var reviewService: ReviewService

    @Autowired
    private lateinit var mvc: MockMvc

    @Test
    fun list() {
        val reviews = listOf(Review(name = "Jack", score = 3, description = "Cool!"))
        given(reviewService.getReviews()).willReturn(reviews)

        mvc.perform(get("/reviews"))
            .andExpect(status().isOk)
            .andExpect(content().string(containsString("Cool!")))
    }

}