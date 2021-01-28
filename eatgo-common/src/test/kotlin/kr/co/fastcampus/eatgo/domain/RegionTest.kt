package kr.co.fastcampus.eatgo.domain

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.jupiter.api.Test

class RegionTest {

    @Test
    fun creation() {
        val region = Region(name = "서울")

        assertThat(region.name, `is`("서울"))
    }
}