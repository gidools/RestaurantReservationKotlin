package kr.co.fastcampus.eatgo.application

import kr.co.fastcampus.eatgo.domain.Review
import kr.co.fastcampus.eatgo.domain.ReviewRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ReviewService(@Autowired private val reviewRepository: ReviewRepository) {

    fun addReview(restaurantId: Long, review: Review): Review {
        review.restaurantId = restaurantId
        return reviewRepository.save(review)
    }

    fun getReviews(): List<Review> {
        return reviewRepository.findAll()
    }

}
