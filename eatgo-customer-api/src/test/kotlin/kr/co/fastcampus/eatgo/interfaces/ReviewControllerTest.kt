package kr.co.fastcampus.eatgo.interfaces

import com.nhaarman.mockito_kotlin.*
import kr.co.fastcampus.eatgo.application.ReviewService
import kr.co.fastcampus.eatgo.domain.Review
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.header
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@ExtendWith(SpringExtension::class)
@WebMvcTest(ReviewController::class)
class ReviewControllerTest {

    private val token =
        "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjEwMDQsIm5hbWUiOiJKYWNrIn0.8XJ_1fcyE2zpZI162crvilIlaNsRhnQPc90mKok0k48"

    @MockBean
    private lateinit var reviewService: ReviewService

    @Autowired
    private lateinit var mvc: MockMvc

    @Test
    fun createWithValidAttributes() {
        val restaurantId = 1L
        val score = 3
        val name = "Jack"
        val description = "good"
        val review = Review(id = 1004, name = name, score = score, description = description)
        given(reviewService.addReview(any(), any(), any(), any())).willReturn(review)

        mvc.perform(post("/restaurants/$restaurantId/reviews")
                .header("Authorization", "Bearer $token")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"score\":$score, \"description\":\"$description\"}"))
            .andExpect(status().isCreated)
            .andExpect(header().string("location", "/restaurants/1/reviews/${review.id}"))

        verify(reviewService).addReview(restaurantId, name, score, description)
    }

    @Test
    fun createWithInvalidAttributes() {
        mvc.perform(
            post("/restaurants/1/reviews")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
            .andExpect(status().isBadRequest)

        verify(reviewService, never()).addReview(any(), any(), any(), any())
    }

}