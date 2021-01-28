package kr.co.fastcampus.eatgo.interfaces

import kr.co.fastcampus.eatgo.application.RegionService
import kr.co.fastcampus.eatgo.domain.Region
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
class RegionController {

    @Autowired
    private lateinit var regionService: RegionService

    @GetMapping(API_REGIONS)
    fun list(): List<Region> {
        return regionService.getRegions()
    }

    @PostMapping(Companion.API_REGIONS)
    fun create(@RequestBody resource: Region): ResponseEntity<Any> {
        val created = regionService.addRegion(resource.name)
        val url = "/regions/${created.id}"
        return ResponseEntity.created(URI(url)).body("{}")
    }

    companion object {
        const val API_REGIONS = "/regions"
    }
}