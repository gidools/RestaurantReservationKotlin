package kr.co.fastcampus.eatgo.application

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.times
import kr.co.fastcampus.eatgo.domain.MenuItem
import kr.co.fastcampus.eatgo.domain.MenuItemRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
class MenuItemServiceTest {

    @Mock
    private lateinit var menuItemRepository: MenuItemRepository

    private lateinit var sut: MenuItemService

    @BeforeEach
    fun setup() {
        sut = MenuItemService(menuItemRepository)
    }

    @Test
    fun bulkUpdate() {
        val menuItems = listOf(
                MenuItem(name = "Kimchi"),
                MenuItem(name = "Gukbob")
        )

        sut.bulkUpdate(1L, menuItems)

        verify(menuItemRepository, times(2)).save(any())
    }

}