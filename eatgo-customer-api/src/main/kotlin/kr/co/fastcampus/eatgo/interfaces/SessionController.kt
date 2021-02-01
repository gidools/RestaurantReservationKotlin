package kr.co.fastcampus.eatgo.interfaces

import kr.co.fastcampus.eatgo.application.UserService
import kr.co.fastcampus.eatgo.domain.User
import kr.co.fastcampus.eatgo.domain.accessToken
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

    @PostMapping(API_SESSION)
    fun create(@RequestBody resource: SessionRequestDto): ResponseEntity<SessionResponseDto> {
        val user = userService.authenticate(resource.email, resource.password)
        val url = "/session"
        val sessionDto = SessionResponseDto(user.accessToken)
        return ResponseEntity.created(URI(url)).body(sessionDto)
    }

    companion object {
        const val API_SESSION = "/session"
    }
}