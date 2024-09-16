package ru.ezhov.dynamicdataretrievalfromdatabase.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ru.ezhov.dynamicdataretrievalfromdatabase.application.DynamicDataRetrievalApplication

@RestController
@RequestMapping("/api/dyn-s")
class DynamicDataRetrievalRestController(
    private val dynamicDataRetrievalApplication: DynamicDataRetrievalApplication
) {
    @GetMapping
    fun get(
        @RequestParam("selName") selName: String,
        @RequestParam("selMap") selMap: String,
        @RequestParam("selFil", required = false) selFil: String?,
    ): List<Map<String, Any?>> =
        dynamicDataRetrievalApplication.get(
            selName,
            selMap.split(","),
            selFil.orEmpty().split(",").filter { it.isNotBlank() },
        )
}