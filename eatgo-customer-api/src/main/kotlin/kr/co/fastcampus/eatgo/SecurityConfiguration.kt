package kr.co.fastcampus.eatgo

import kr.co.fastcampus.eatgo.utils.JwtUtil
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
@EnableWebSecurity
class SecurityConfiguration : WebSecurityConfigurerAdapter() {

    @Value(value = "\${jwt.secret}")
    private lateinit var secret: String

    override fun configure(web: WebSecurity) {
        web.ignoring()
            .antMatchers("/h2-console/**")
            .antMatchers("/**")
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun jwtUtil(): JwtUtil {
        return JwtUtil(secret)
    }
}