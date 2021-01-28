package kr.co.fastcampus.eatgo.interfaces

import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.verify
import kr.co.fastcampus.eatgo.application.RegionService
import kr.co.fastcampus.eatgo.domain.Region
import org.hamcrest.CoreMatchers.containsString
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@ExtendWith(SpringExtension::class)
@WebMvcTest(RegionController::class)
class RegionControllerTest {

    @Autowired
    private lateinit var mvc: MockMvc

    @MockBean
    private lateinit var regionService: RegionService

    @Test
    fun list() {
        getMockRegions()

        mvc.perform(get(RegionController.API_REGIONS))
                .andExpect(status().isOk)
                .andExpect(content().string(containsString("Seoul")))

        verify(regionService).getRegions()
    }

    @Test
    internal fun create() {
        TODO("Not yet implemented")
    }

    private fun getMockRegions() {
        given(regionService.getRegions()).willReturn(
                listOf(
                        Region(name = "Seoul"),
                        Region(name = "Busan")
                ))
    }
}