package kr.co.fastcampus.eatgo.utils

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Component

@Component
class JwtUtil {

    fun createToken(userId: Long, name: String): String {
        val secret = "12345678901234567890123456789012"
        val key = Keys.hmacShaKeyFor(secret.toByteArray())
        val token = Jwts.builder()
                .claim("userId", userId)
                .claim("name", name)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact()
        return token
    }

}
