package kr.co.fastcampus.eatgo.interfaces

import kr.co.fastcampus.eatgo.application.UserService
import kr.co.fastcampus.eatgo.domain.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
class UserController {

    @Autowired
    private lateinit var userService: UserService

    @PostMapping(API_USERS)
    fun create(@RequestBody resource: User): ResponseEntity<Any> {
        val created = userService.registerUser(resource.email, resource.name, resource.password)
        val url = "/users/" + created.id
        return ResponseEntity.created(URI(url)).body("{}")
    }

    companion object {
        const val API_USERS = "/users"
    }

}