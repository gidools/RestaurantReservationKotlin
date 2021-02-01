package kr.co.fastcampus.eatgo.utils

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Component
import javax.crypto.SecretKey

@Component
class JwtUtil(secret: String) {

    private val key: SecretKey = Keys.hmacShaKeyFor(secret.toByteArray())

    fun createToken(userId: Long, name: String): String {
        val token = Jwts.builder()
            .claim("userId", userId)
            .claim("name", name)
            .signWith(key, SignatureAlgorithm.HS256)
            .compact()
        return token
    }

    fun getClaims(token: String): Claims {
        return Jwts.parser()
            .setSigningKey(key)
            .parseClaimsJws(token)
            .body
    }

}
