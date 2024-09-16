package ru.ezhov.dynamicdataretrievalfromdatabase.application

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Service
import ru.ezhov.dynamicdataretrievalfromdatabase.infrastructure.SampleDescriptionRepository

@Service
class DynamicDataRetrievalApplication(
    private val jdbcTemplate: JdbcTemplate,
    private val sampleDescriptionRepository: SampleDescriptionRepository,
) {
    fun get(
        name: String,
        outputMapping: List<String>,
        filterValues: List<String>,
    ): List<Map<String, Any?>> {
        val description = sampleDescriptionRepository.findAllByName(name) ?: throw IllegalArgumentException("Not found")

        val mappingNames = description.outputMapping.filterValues { outputMapping.contains(it) }
        val list = mutableListOf<Map<String, Any?>>()

        jdbcTemplate.query({ con ->
            con.prepareStatement(description.query).apply {
                if (filterValues.isNotEmpty()) {
                    filterValues.forEachIndexed { index, s ->
                        setObject(index + 1, s)
                    }
                }
            }
        }, { rs ->
            val metadata = rs.metaData

            val names = (1..metadata.columnCount).map {
                metadata.getColumnName(it)
            }

            val row = names.associateWith { name -> rs.getObject(name) }

            val mappingRow = row.filterKeys { key -> mappingNames.containsKey(key) }.map { mappingNames[it.key]!! to it.value }.toMap()

            if (mappingRow.isNotEmpty()) {
                list.add(mappingRow)
            }
        }
        )

        return list
    }
}
