package kr.co.fastcampus.eatgo.utils

import org.hamcrest.CoreMatchers.containsString
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.jupiter.api.Test

class JwtUtilTest {

    @Test
    fun createToken() {
        val jwtUtil = JwtUtil()
        val token = jwtUtil.createToken(1004L, "Jack")

        assertThat(token, containsString("."))
    }
}