package ru.ezhov.dynamicdataretrievalfromdatabase.application

import org.springframework.stereotype.Service
import ru.ezhov.dynamicdataretrievalfromdatabase.core.DynamicDataRetrievalDbFactory
import ru.ezhov.dynamicdataretrievalfromdatabase.infrastructure.InMemorySampleDescriptionRepository
import javax.sql.DataSource

@Service
class DynamicDataRetrievalApplication(
    private val dataSource: DataSource,
    private val inMemorySampleDescriptionRepository: InMemorySampleDescriptionRepository,
) {
    fun get(
        name: String,
        outputMapping: List<String>,
        filterValues: List<String>,
    ): List<Map<String, Any?>> {
        val description = inMemorySampleDescriptionRepository.byName(name) ?: throw IllegalArgumentException("Not found")

        val mappingNames = when (outputMapping.isEmpty()) {
            true -> description.outputMapping
            false -> description.outputMapping.filterValues { outputMapping.contains(it) }
        }

        return DynamicDataRetrievalDbFactory
            .get(dataSource)
            .data(
                query = description.query,
                outputMappingNames = mappingNames,
                filterValues = filterValues
            )
    }
}
