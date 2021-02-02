package kr.co.fastcampus.eatgo

import kr.co.fastcampus.eatgo.utils.JwtUtil
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class EatgoAdminApplication {

	@Value(value = "\${jwt.secret}")
	private lateinit var secret: String

	@Bean
	fun jwtUtil(): JwtUtil {
		return JwtUtil(secret)
	}
}

fun main(args: Array<String>) {
	runApplication<EatgoAdminApplication>(*args)
}
