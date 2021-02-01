package kr.co.fastcampus.eatgo.application

import kr.co.fastcampus.eatgo.domain.User
import kr.co.fastcampus.eatgo.domain.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserService(@Autowired private val userRepository: UserRepository) {

    fun registerUser(email: String, name: String, password: String): User {
        return userRepository.save(User(email = email, name = name, password = password))
    }

}
