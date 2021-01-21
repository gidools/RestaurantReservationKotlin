package kr.co.fastcampus.eatgo.domain

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.jupiter.api.Test

class RestaurantTests {

    @Test
    fun creation() {
        val restaurant = Restaurant(name = "Bob zip", address = "")

        assertThat(restaurant.name, `is`("Bob zip"))
    }

    @Test
    internal fun address() {
        val restaurant = Restaurant(name = "Bob zip", address = "Seoul")

        assertThat(restaurant.address, `is`("Seoul"))
    }

    @Test
    internal fun information() {
        val restaurant = Restaurant(name = "Bob zip", address = "Seoul")

        assertThat(restaurant.information, `is`("Bob zip in Seoul"))
    }
    
}