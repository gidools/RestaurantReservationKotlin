package kr.co.fastcampus.eatgo.application

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.verify
import kr.co.fastcampus.eatgo.domain.Category
import kr.co.fastcampus.eatgo.domain.CategoryRepository
import org.hamcrest.MatcherAssert
import org.hamcrest.core.Is
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
class CategoryServiceTest {

    private lateinit var sut: CategoryService

    @Mock
    private lateinit var categoryRepository: CategoryRepository

    @BeforeEach
    fun setup() {
        sut = CategoryService(categoryRepository)
    }

    @Test
    fun getCategories() {
        getMockCategories()

        val category = sut.getCategories()

        val region = category[0]
        MatcherAssert.assertThat(region.name, Is.`is`("Korean"))
    }

    @Test
    fun addCategory() {
        addMockCategory()

        val category = Category(name = "Korean")
        val created = sut.addCategory(category.name)

        verify(categoryRepository).save(category)
        MatcherAssert.assertThat(created.name, Is.`is`(category.name))
    }

    private fun addMockCategory() {
        given(categoryRepository.save(any())).willReturn(Category(1004L, "Korean"))
    }

    private fun getMockCategories() {
        given(categoryRepository.findAll()).willReturn(
                listOf(
                        Category(name = "Korean"),
                        Category(name = "Chinese")
                )
        )
    }
}