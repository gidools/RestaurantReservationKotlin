package kr.co.fastcampus.eatgo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class EatgoCustomerApplication

fun main(args: Array<String>) {
	runApplication<EatgoCustomerApplication>(*args)
}
