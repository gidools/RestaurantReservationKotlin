package kr.co.fastcampus.eatgo.application

import kr.co.fastcampus.eatgo.domain.Category
import kr.co.fastcampus.eatgo.domain.CategoryRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CategoryService(@Autowired private val categoryRepository: CategoryRepository) {

    fun getCategories(): List<Category> {
        return categoryRepository.findAll()
    }

    fun addCategory(name: String): Category {
        return categoryRepository.save(Category(name = name))
    }

}
