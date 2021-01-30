package kr.co.fastcampus.eatgo.interfaces

import kr.co.fastcampus.eatgo.application.CategoryService
import kr.co.fastcampus.eatgo.domain.Category
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
class CategoryController {

    @Autowired
    private lateinit var categoryService: CategoryService

    @GetMapping(API_CATEGORIES)
    fun list(): List<Category> {
        return categoryService.getCategories()
    }

    @PostMapping(Companion.API_CATEGORIES)
    fun create(@RequestBody resource: Category): ResponseEntity<Any> {
        val created = categoryService.addCategory(resource.name)
        val url = "${API_CATEGORIES}/${created.id}"
        return ResponseEntity.created(URI(url)).body("{}")
    }

    companion object {
        const val API_CATEGORIES = "/categories"
    }

}