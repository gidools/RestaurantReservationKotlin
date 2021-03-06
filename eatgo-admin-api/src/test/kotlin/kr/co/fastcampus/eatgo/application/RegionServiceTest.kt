package kr.co.fastcampus.eatgo.application

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.verify
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

    @Test
    fun addRegion() {
        addMockRegion()

        val region = Region(name = "Seoul")
        val created = sut.addRegion(region.name)

        verify(regionRepository).save(region)
        assertThat(created.name, `is`(region.name))
    }

    private fun addMockRegion() {
        given(regionRepository.save(any())).willReturn(Region(1004L, "Seoul"))
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