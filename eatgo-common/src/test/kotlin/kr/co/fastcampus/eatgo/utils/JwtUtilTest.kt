package kr.co.fastcampus.eatgo.utils

import org.hamcrest.CoreMatchers.containsString
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.Test

class JwtUtilTest {

    @Test
    fun createToken() {
        val secret = "12345678901234567890123456789012"
        val jwtUtil = JwtUtil(secret)
        val token = jwtUtil.createToken(1004L, "Jack")

        assertThat(token, containsString("."))
    }
}