package kr.co.fastcampus.eatgo.interfaces

import kr.co.fastcampus.eatgo.application.UserService
import kr.co.fastcampus.eatgo.utils.JwtUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
class SessionController {

    @Autowired
    private lateinit var userService: UserService

    @Autowired
    private lateinit var jwtUtl: JwtUtil

    @PostMapping(API_SESSION)
    fun create(@RequestBody resource: SessionRequestDto): ResponseEntity<SessionResponseDto> {
        val user = userService.authenticate(resource.email, resource.password)
        val accessToken = jwtUtl.createToken(user.id!!, user.name, user.restaurantId)
        val url = API_SESSION
        val sessionDto = SessionResponseDto(accessToken)
        return ResponseEntity.created(URI(url)).body(sessionDto)
    }

    companion object {
        const val API_SESSION = "/session"
    }
}