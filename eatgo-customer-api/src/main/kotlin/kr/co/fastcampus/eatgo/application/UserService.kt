package kr.co.fastcampus.eatgo.application

import kr.co.fastcampus.eatgo.domain.User
import kr.co.fastcampus.eatgo.domain.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(@Autowired private val userRepository: UserRepository) {

    fun registerUser(email: String, name: String, password: String): User {
        val existed = userRepository.findByEmail(email)
        if (existed.isPresent) {
            throw DuplicateEmailException(email)
        }

        val pwdEncoder = BCryptPasswordEncoder()
        val encodedPwd = pwdEncoder.encode(password)
        return userRepository.save(User(email = email, name = name, password = encodedPwd))
    }

}
