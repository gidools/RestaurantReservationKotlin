package kr.co.fastcampus.eatgo.application

import java.lang.RuntimeException

class EmailNotExistedException(email: String): RuntimeException("Email is not registered : $email")
