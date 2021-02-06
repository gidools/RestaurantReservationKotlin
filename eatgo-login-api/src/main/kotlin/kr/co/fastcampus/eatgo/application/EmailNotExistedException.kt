package kr.co.fastcampus.eatgo.application

class EmailNotExistedException(email: String): RuntimeException("Email is not registered : $email")
