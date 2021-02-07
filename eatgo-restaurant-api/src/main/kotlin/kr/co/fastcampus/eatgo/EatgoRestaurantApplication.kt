package kr.co.fastcampus.eatgo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class EatgoRestaurantApplication

fun main(args: Array<String>) {
	runApplication<EatgoRestaurantApplication>(*args)
}
