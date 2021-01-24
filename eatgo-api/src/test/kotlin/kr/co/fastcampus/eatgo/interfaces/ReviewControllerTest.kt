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

    @MockBean
    private lateinit var reviewService: ReviewService

    @Autowired
    private lateinit var mvc: MockMvc

    @Test
    fun createWithValidAttributes() {
        val review = Review(id = 1004, name = "Jack", score = 3, description = "good")
        given(reviewService.addReview(any(), any())).willReturn(review)

        mvc.perform(post("/restaurants/1/reviews")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Jack\", \"score\":3, \"description\":\"good\"}"))
                .andExpect(status().isCreated)
                .andExpect(header().string("location", "/restaurants/1/reviews/1004"))

        verify(reviewService).addReview(any(), any())
    }

    @Test
    fun createWithInvalidAttributes() {
        given(reviewService.addReview(any(), any())).willReturn(Review(id = 1004L))

        mvc.perform(post("/restaurants/1/reviews")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"\", \"score\":3, \"description\":\"good\"}"))
                .andExpect(status().isBadRequest)

        verify(reviewService, never()).addReview(any(), any())
    }

}