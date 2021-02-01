package kr.co.fastcampus.eatgo.utils

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Component
import javax.crypto.SecretKey

@Component
class JwtUtil(private val secret: String) {

    val key: SecretKey = Keys.hmacShaKeyFor(secret.toByteArray())

    fun createToken(userId: Long, name: String): String {
        val token = Jwts.builder()
                .claim("userId", userId)
                .claim("name", name)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact()
        return token
    }

}
