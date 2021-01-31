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
    }
}