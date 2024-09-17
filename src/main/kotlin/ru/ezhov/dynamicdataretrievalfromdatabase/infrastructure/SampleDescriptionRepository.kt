package ru.ezhov.dynamicdataretrievalfromdatabase.infrastructure

import org.springframework.stereotype.Repository
import ru.ezhov.dynamicdataretrievalfromdatabase.domain.model.SampleDescription

@Repository
class InMemorySampleDescriptionRepository {
    private val examples = listOf(
        SampleDescription(
            name = "select-customer",
            outputMapping = mapOf(
                "FIRST_NAME" to "firstName",
                "CITY" to "city",
                "ID" to "id",
            ),
            query = """
                select * from CUSTOMER c 
            """.trimIndent(),
        ),

        SampleDescription(
            name = "search-customer",
            outputMapping = mapOf(
                "FIRST_NAME" to "firstName",
                "CITY" to "city",
                "ID" to "id",
            ),
            query = """
                select * from CUSTOMER c where FIRST_NAME like '%' || ? || '%'
            """.trimIndent(),
        ),

        SampleDescription(
            name = "sum-of-customer",
            outputMapping = mapOf(
                "FIRST_NAME" to "firstName",
                "CITY" to "city",
                "COUNTRY" to "country",
                "SUM_OF_TOTAL" to "total",
            ),
            query = """
                SELECT
                    c.FIRST_NAME,
                    c.CITY ,
                    c.COUNTRY ,
                    t0.SUM_OF_TOTAL
                FROM
                    (
                    SELECT
                        o.CUSTOMER_ID,
                        SUM(o.TOTAL_AMOUNT) AS SUM_OF_TOTAL
                    FROM
                        "ORDER" o
                    GROUP BY
                        o.CUSTOMER_ID 
                ) t0
                INNER JOIN CUSTOMER c ON
                    t0.CUSTOMER_ID = c.ID 
            """.trimIndent(),
        )
    )

    fun byName(name: String): SampleDescription? = examples.firstOrNull { it.name == name }
}