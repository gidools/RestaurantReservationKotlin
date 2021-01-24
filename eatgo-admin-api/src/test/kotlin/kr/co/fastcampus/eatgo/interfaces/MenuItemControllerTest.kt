package kr.co.fastcampus.eatgo.interfaces

import com.nhaarman.mockito_kotlin.given
import kr.co.fastcampus.eatgo.application.MenuItemService
import kr.co.fastcampus.eatgo.domain.MenuItem
import org.hamcrest.CoreMatchers.containsString
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers.eq
import org.mockito.Mockito.anyList
import org.mockito.Mockito.verify
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@ExtendWith(SpringExtension::class)
@WebMvcTest(MenuItemController::class)
class MenuItemControllerTest {

    @Autowired
    private lateinit var mvc: MockMvc

    @MockBean
    private lateinit var menuItemService: MenuItemService

    @Test
    fun bulkUpdate() {
        mvc.perform(patch("/restaurants/1/menuitems")
                .contentType(MediaType.APPLICATION_JSON)
                .content("[]"))
                .andExpect(status().isOk)

        verify(menuItemService).bulkUpdate(eq(1L), anyList())
    }

    @Test
    fun list() {
        given(menuItemService.getMenuItems(1004L)).willReturn(
            listOf(
                MenuItem(name = "Kimchi")
            )
        )

        mvc.perform(get("/restaurants/1004/menuitems")
            .contentType(MediaType.APPLICATION_JSON)
            .content("[]"))
            .andExpect(status().isOk)
            .andExpect(content().string(containsString("Kimchi")))

        verify(menuItemService).getMenuItems(1004L)
    }

}