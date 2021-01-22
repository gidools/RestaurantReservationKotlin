package kr.co.fastcampus.eatgo.interfaces

import kr.co.fastcampus.eatgo.domain.Restaurant
import kr.co.fastcampus.eatgo.domain.RestaurantRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class RestaurantController {

    private val repository = RestaurantRepository()

    @GetMapping(GET_RESTAURANTS)
    fun list(): List<Restaurant> {
        return repository.findAll()
    }

    @GetMapping("$GET_RESTAURANTS/{id}")
    fun detail(@PathVariable("id") id: Long): Restaurant? {
        return repository.findById(id)
    }

    companion object {
        const val GET_RESTAURANTS = "/restaurants"
    }

}