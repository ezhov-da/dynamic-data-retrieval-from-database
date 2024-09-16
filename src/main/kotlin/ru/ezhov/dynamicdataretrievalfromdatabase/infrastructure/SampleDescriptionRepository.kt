package ru.ezhov.dynamicdataretrievalfromdatabase.infrastructure

import org.springframework.data.jpa.repository.JpaRepository
import ru.ezhov.dynamicdataretrievalfromdatabase.domain.model.SampleDescription

interface SampleDescriptionRepository : JpaRepository<SampleDescription, String> {
    fun findAllByName(name: String): SampleDescription?
}