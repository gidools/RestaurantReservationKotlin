package kr.co.fastcampus.eatgo.domain

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.jupiter.api.Test

class UserTest {

    @Test
    fun creation() {
        val result = User(0L, "test@example.com", "test", level = 100)

        assertThat(result.name, `is`("test"))
        assertThat(result.isAdmin, `is`(true))
        assertThat(result.isActive, `is`(true))
    }

    @Test
    internal fun restaurantOwner() {
        val result = User(email = "test@example.com", name = "tester", level = 1L)

        assertThat(result.isRestaurantOwner, `is`(false))

        result.restaurantId = 1004L

        assertThat(result.isRestaurantOwner, `is`(true))
        assertThat(result.restaurantId, `is`(1004L))

    }
}