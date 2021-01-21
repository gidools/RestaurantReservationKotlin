package kr.co.fastcampus.eatgo.domain

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.jupiter.api.Test

class RestaurantTests {

    @Test
    fun creation() {
        val restaurant = Restaurant(id = 1004, name = "Bob zip", address = "")

        assertThat(restaurant.id, `is`(1004L))
        assertThat(restaurant.name, `is`("Bob zip"))
    }

    @Test
    internal fun address() {
        val restaurant = Restaurant(name = "Bob zip", address = "Seoul", id = 1004)

        assertThat(restaurant.address, `is`("Seoul"))
    }

    @Test
    internal fun information() {
        val restaurant = Restaurant(name = "Bob zip", address = "Seoul", id = 1004)

        assertThat(restaurant.information, `is`("Bob zip in Seoul"))
    }
    
}