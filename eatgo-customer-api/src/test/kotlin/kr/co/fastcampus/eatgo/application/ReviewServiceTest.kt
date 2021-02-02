package kr.co.fastcampus.eatgo.application

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.verify
import kr.co.fastcampus.eatgo.domain.Review
import kr.co.fastcampus.eatgo.domain.ReviewRepository
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
    fun addReview() {
        sut.addReview(1L, "Jack", 3, "Tasty")

        verify(reviewRepository).save(any())
    }
}