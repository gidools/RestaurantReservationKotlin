package kr.co.fastcampus.eatgo.application

import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.verify
import kr.co.fastcampus.eatgo.domain.Review
import kr.co.fastcampus.eatgo.domain.ReviewRepository
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
class ReviewServiceTest {

    @Mock
    private lateinit var reviewRepository: ReviewRepository

    private lateinit var sut: ReviewService

    @BeforeEach
    fun setup() {
        sut = ReviewService(reviewRepository)
    }

    @Test
    internal fun getReviews() {
        val reviews = listOf(Review(name = "Jack", score = 3, description = "Cool!"))
        given(reviewRepository.findAll()).willReturn(reviews)

        val result = sut.getReviews()

        verify(reviewRepository).findAll()
        assertThat(result[0].description, `is`("Cool!"))
    }

}