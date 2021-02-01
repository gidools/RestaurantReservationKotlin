package kr.co.fastcampus.eatgo.domain

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.jupiter.api.Test

class UserTest {

    @Test
    fun creation() {
        val result = User(0L, "test@example.com", "test", 100)

        assertThat(result.name, `is`("test"))
        assertThat(result.isAdmin, `is`(true))
        assertThat(result.isActive, `is`(true))
    }

    @Test
    internal fun accessToken() {
        val userToken = "ACCESSTOKEN"
        val accessToken = userToken.substring(0, 10)
        val user = User(name = "", email = "", password = userToken)
        assertThat(user.accessToken, `is`(accessToken))
    }
}