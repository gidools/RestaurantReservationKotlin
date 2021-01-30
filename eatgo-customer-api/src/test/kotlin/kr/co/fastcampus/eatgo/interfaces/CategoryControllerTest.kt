package kr.co.fastcampus.eatgo.interfaces

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.verify
import kr.co.fastcampus.eatgo.application.CategoryService
import kr.co.fastcampus.eatgo.domain.Category
import kr.co.fastcampus.eatgo.domain.Region
import org.hamcrest.CoreMatchers
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@ExtendWith(SpringExtension::class)
@WebMvcTest(CategoryController::class)
class CategoryControllerTest {

    @Autowired
    private lateinit var mvc: MockMvc

    @MockBean
    private lateinit var categoryService: CategoryService

    @Test
    fun list() {
        getMockCategories()

        mvc.perform(MockMvcRequestBuilders.get(CategoryController.API_CATEGORIES))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.content().string(CoreMatchers.containsString("Korean")))

        verify(categoryService).getCategories()
    }

    private fun getMockCategories() {
        given(categoryService.getCategories()).willReturn(
                listOf(
                        Category(name = "Korean"),
                        Category(name = "Chinese")
                ))
    }

}