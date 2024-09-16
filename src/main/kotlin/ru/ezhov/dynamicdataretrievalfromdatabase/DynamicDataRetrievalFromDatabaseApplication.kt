package ru.ezhov.dynamicdataretrievalfromdatabase

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DynamicDataRetrievalFromDatabaseApplication {
    @org.springframework.context.annotation.Bean
    @org.springframework.boot.context.properties.ConfigurationProperties("spring.datasource")
    fun dataSource(): javax.sql.DataSource {
        return org.springframework.boot.jdbc.DataSourceBuilder.create().build()
    }
}

fun main(args: Array<String>) {
    runApplication<DynamicDataRetrievalFromDatabaseApplication>(*args)
}
