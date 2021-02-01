package kr.co.fastcampus.eatgo.utils

import org.hamcrest.CoreMatchers.containsString
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class JwtUtilTest {

    private val secret = "12345678901234567890123456789012"
    private lateinit var sut: JwtUtil

    @BeforeEach
    fun setup() {
        sut = JwtUtil(secret)
    }

    @Test
    fun createToken() {
        val token = sut.createToken(1004L, "Jack")

        assertThat(token, containsString("."))
    }

    @Test
    internal fun getClaims() {
        val token = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjEwMDQsIm5hbWUiOiJKYWNrIn0.8XJ_1fcyE2zpZI162crvilIlaNsRhnQPc90mKok0k48"
        val result = sut.getClaims(token)

        assertThat(result["name"] as String, `is`("Jack"))
    }
}