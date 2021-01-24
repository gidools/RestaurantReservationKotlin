package kr.co.fastcampus.eatgo.domain

import java.lang.RuntimeException

class RestaurantNotFoundException(id: Long) : RuntimeException("Could not find restaurant $id")
