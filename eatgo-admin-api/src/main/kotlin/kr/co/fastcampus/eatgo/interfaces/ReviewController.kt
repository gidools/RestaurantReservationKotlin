package kr.co.fastcampus.eatgo.interfaces

import kr.co.fastcampus.eatgo.application.ReviewService
import kr.co.fastcampus.eatgo.domain.Review
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.net.URI
import javax.validation.Valid

@RestController
class ReviewController {

    @Autowired
    private lateinit var reviewService: ReviewService

    @PostMapping(API_REVIEW)
    fun create(
            @PathVariable("restaurantId") restaurantId: Long,
            @Valid @RequestBody review: Review): ResponseEntity<Any> {
        val created = reviewService.addReview(restaurantId, review)
        val url = URI("/restaurants/${restaurantId}/reviews/${created.id}")
        return ResponseEntity.created(url).body("{}")
    }

    companion object {
        const val API_REVIEW = "/restaurants/{restaurantId}/reviews"
    }
}