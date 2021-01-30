package kr.co.fastcampus.eatgo.domain

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.jupiter.api.Test

class CategoryTest {

    @Test
    fun create() {
        val result = Category(name = "Korean food")

        assertThat(result.name, `is`("Korean food"))
    }
}