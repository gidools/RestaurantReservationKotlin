package kr.co.fastcampus.eatgo.interfaces

import kr.co.fastcampus.eatgo.application.UserService
import kr.co.fastcampus.eatgo.domain.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI
import javax.validation.Valid

@RestController
class UserController {

    @Autowired
    private lateinit var userService: UserService

    @GetMapping(API_USERS)
    fun list(): List<User> {
        return userService.getUsers()
    }

    @PostMapping(API_USERS)
    fun create(@Valid @RequestBody resource: User): ResponseEntity<Any> {
        val created = userService.addUser(email = resource.email, name = resource.name, level = resource.level)
        val location = URI("/${API_USERS}/${created.id}")
        return ResponseEntity.created(location).body("{}")
    }

    @PatchMapping("${API_USERS}/{id}")
    fun update(@PathVariable("id") id: Long, @RequestBody resource: User): String {
        userService.updateUser(id = id, email = resource.email, name = resource.name, level = resource.level)
        return "{}"
    }

    companion object {
        const val API_USERS = "/users"
    }
}