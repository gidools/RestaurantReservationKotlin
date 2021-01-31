package kr.co.fastcampus.eatgo.application

import kr.co.fastcampus.eatgo.domain.User
import kr.co.fastcampus.eatgo.domain.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserService(@Autowired private val userRepository: UserRepository) {

    fun getUsers(): List<User> {
        return userRepository.findAll()
    }

    fun addUser(email: String, name: String, level: Long): User {
        return userRepository.save(User(email = email, name = name, level = level))
    }

    fun updateUser(id: Long, email: String, name: String, level: Long): User {
        val user = userRepository.findById(id).orElse(null)
        user.name = name
        user.email = email
        user.level = level
        return user
    }

}
