package ru.ezhov.dynamicdataretrievalfromdatabase.core

import javax.sql.DataSource

interface DynamicDataRetrievalDb {
    /**
     * @param query Запрос
     * @param outputMappingNames Выходные поля
     * @param filterValues Данные для фильтрации
     */
    fun data(
        query: String,
        outputMappingNames: Map<String, String>,
        filterValues: List<String>,
    ): List<Map<String, Any?>>
}

object DynamicDataRetrievalDbFactory {
    fun get(dataSource: DataSource): DynamicDataRetrievalDb = DynamicDataRetrievalDbImpl(dataSource)
}

class DynamicDataRetrievalDbImpl(
    private val dataSource: DataSource
) : DynamicDataRetrievalDb {
    override fun data(query: String, outputMappingNames: Map<String, String>, filterValues: List<String>): List<Map<String, Any?>> {
        val list = mutableListOf<Map<String, Any?>>()

        return dataSource.connection.use { connection ->
            connection.prepareStatement(query).use { ps ->
                if (filterValues.isNotEmpty()) {
                    filterValues.forEachIndexed { index, s ->
                        ps.setObject(index + 1, s)
                    }
                }

                ps.executeQuery().use { rs ->
                    val metadata = rs.metaData

                    val names = (1..metadata.columnCount)
                        .map { metadata.getColumnName(it) }

                    while (rs.next()) {
                        val row = names.associateWith { name -> rs.getObject(name) }
                        val mappingRow =
                            row
                                .filterKeys { key -> outputMappingNames.containsKey(key) }
                                .map { outputMappingNames[it.key]!! to it.value }
                                .toMap()
                        if (mappingRow.isNotEmpty()) {
                            list.add(mappingRow)
                        }
                    }
                }
            }

            list
        }
    }
}