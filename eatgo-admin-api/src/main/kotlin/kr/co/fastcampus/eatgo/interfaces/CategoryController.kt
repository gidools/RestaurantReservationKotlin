package kr.co.fastcampus.eatgo.interfaces

import kr.co.fastcampus.eatgo.application.CategoryService
import kr.co.fastcampus.eatgo.domain.Category
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

const val API_CATEGORIES = "/categories"

@RestController
@RequestMapping(API_CATEGORIES)
class CategoryController {

    @Autowired
    private lateinit var categoryService: CategoryService

    @GetMapping
    fun list(): List<Category> {
        return categoryService.getCategories()
    }

    @PostMapping
    fun create(@RequestBody resource: Category): ResponseEntity<Any> {
        val created = categoryService.addCategory(resource.name)
        val url = "categories/${created.id}"
        return ResponseEntity.created(URI(url)).body("{}")
    }

}