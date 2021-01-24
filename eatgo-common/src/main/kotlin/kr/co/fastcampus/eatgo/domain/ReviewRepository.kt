package kr.co.fastcampus.eatgo.domain

import org.springframework.data.repository.CrudRepository

interface ReviewRepository: CrudRepository<Review, Long> {

    fun save(review: Review): Review
    override fun findAll(): List<Review>
    fun findAllByRestaurantId(restaurantId: Long): List<Review>

}
