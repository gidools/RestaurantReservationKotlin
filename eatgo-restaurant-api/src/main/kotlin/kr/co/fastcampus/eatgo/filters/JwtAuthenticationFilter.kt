package kr.co.fastcampus.eatgo.filters

import kr.co.fastcampus.eatgo.utils.JwtUtil
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtAuthenticationFilter(authenticationManager: AuthenticationManager, private val jwtUtil: JwtUtil) :
    BasicAuthenticationFilter(authenticationManager) {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        chain: FilterChain) {
        val authentication = getAuthentication(request)
        if (authentication != null) {
            val context = SecurityContextHolder.getContext()
            context.authentication = authentication
        }
        chain.doFilter(request, response)
    }

    private fun getAuthentication(request: HttpServletRequest): Authentication? {
        val token = request.getHeader("Authorization") ?: return null

        val claims = jwtUtil.getClaims(token.substring("Bearer ".length))
        return UsernamePasswordAuthenticationToken(claims, null)
    }
}