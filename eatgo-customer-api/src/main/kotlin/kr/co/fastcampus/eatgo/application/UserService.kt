package kr.co.fastcampus.eatgo.application

import kr.co.fastcampus.eatgo.domain.User
import kr.co.fastcampus.eatgo.domain.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(@Autowired private val userRepository: UserRepository,
                  @Autowired private val passwordEncoder: PasswordEncoder) {

    fun registerUser(email: String, name: String, password: String): User {
        val existed = userRepository.findByEmail(email)
        if (existed.isPresent) {
            throw DuplicateEmailException(email)
        }

        val encodedPwd = passwordEncoder.encode(password)
        return userRepository.save(User(email = email, name = name, password = encodedPwd))
    }

    fun authenticate(email: String, password: String): User {
        val user = userRepository.findByEmail(email).orElseThrow { EmailNotExistedException(email) }
        if (!passwordEncoder.matches(password, user.password)) {
            throw PasswordWrongException()
        }

        return user
    }

}
