package kr.co.fastcampus.eatgo.application

import java.lang.RuntimeException

class DuplicateEmailException(email: String): RuntimeException("Duplicate email exception : $email") {

}
