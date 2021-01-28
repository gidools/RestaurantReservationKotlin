package kr.co.fastcampus.eatgo.application

import com.nhaarman.mockito_kotlin.given
import kr.co.fastcampus.eatgo.domain.Region
import kr.co.fastcampus.eatgo.domain.RegionRepository
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
class RegionServiceTest {

    private lateinit var sut: RegionService

    @Mock
    private lateinit var regionRepository: RegionRepository

    @BeforeEach
    fun setup() {
        sut = RegionService(regionRepository)
    }

    @Test
    fun getRegions() {
        getMockRegions()

        val regions = sut.getRegions()

        val region = regions[0]
        assertThat(region.name, `is`("Seoul"))
    }

    private fun getMockRegions() {
        given(regionRepository.findAll()).willReturn(
                listOf(
                        Region(name = "Seoul"),
                        Region(name = "Busan")
                )
        )
    }
}