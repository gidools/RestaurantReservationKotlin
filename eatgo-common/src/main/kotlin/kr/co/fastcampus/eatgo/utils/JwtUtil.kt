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

    fun createToken(userId: Long, name: String, restaurantId: Long? = null): String {
        var builder = Jwts.builder()
            .claim("userId", userId)
            .claim("name", name)

        if (restaurantId != null) {
            builder = builder.claim("restaurantId", restaurantId)
        }

        return builder
            .signWith(key, SignatureAlgorithm.HS256)
            .compact()
    }

    fun getClaims(token: String): Claims {
        return Jwts.parser()
            .setSigningKey(key)
            .parseClaimsJws(token)
            .body
    }

}
