package kr.co.fastcampus.eatgo.domain

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class RestaurantRepositoryImplTest {

    private lateinit var sut: RestaurantRepositoryImpl

    @BeforeEach
    fun setup() {
        sut = RestaurantRepositoryImpl()
    }

    @Test
    fun repository_success_correctSizeReturned() {
        val oldCount = sut.findAll().size
        val restaurant = Restaurant("Bob zip", "Seoul")

        val result = sut.save(restaurant)

        val newCount = sut.findAll().size

        assertThat(newCount, `is`(oldCount + 1))
        assertThat(result.id, `is`(1234L))
    }
}