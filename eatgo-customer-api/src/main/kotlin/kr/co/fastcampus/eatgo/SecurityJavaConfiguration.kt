package kr.co.fastcampus.eatgo

import kr.co.fastcampus.eatgo.filters.JwtAuthenticationFilter
import kr.co.fastcampus.eatgo.utils.JwtUtil
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
@EnableWebSecurity
class SecurityJavaConfiguration : WebSecurityConfigurerAdapter() {

    @Value(value = "\${jwt.secret}")
    private lateinit var secret: String

    override fun configure(http: HttpSecurity) {
        val filter = JwtAuthenticationFilter(authenticationManager(), jwtUtil())

        http.cors().disable()
            .csrf().disable()
            .formLogin().disable()
            .headers().frameOptions().disable()
            .and()
            .addFilter(filter)
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
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