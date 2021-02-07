package kr.co.fastcampus.eatgo.utils

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
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
        val token = sut.createToken(2020L, "Owner", 1004L)
        val split = token.split(".")

        assertThat(token, containsString("."))
        assertThat(split.size, `is`(3))
    }

    @Test
    fun getClaims() {
        val token = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjEwMDQsIm5hbWUiOiJKYWNrIn0.8XJ_1fcyE2zpZI162crvilIlaNsRhnQPc90mKok0k48"
        val result = sut.getClaims(token)

        assertThat(result["name"], `is`("Jack"))
        assertThat(result.get("userId", Long::class.javaObjectType), `is`(1004L))

        val endIndex = token.lastIndexOf('.')
        val withoutSignature = token.substring(0, endIndex + 1);

        val claims: Claims  = Jwts.parser().parse(withoutSignature).body as Claims
        assertThat(claims["name"] as String, `is`("Jack"))
    }
}