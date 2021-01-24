package kr.co.fastcampus.eatgo.interfaces

import kr.co.fastcampus.eatgo.application.ReviewService
import kr.co.fastcampus.eatgo.domain.Review
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI
import javax.validation.Valid

@RestController
class ReviewController {

    @Autowired
    private lateinit var reviewService: ReviewService

    @GetMapping(API_REVIEW)
    fun list(): List<Review> {
        return reviewService.getReviews()
    }

    companion object {
        const val API_REVIEW = "/reviews"
    }
}