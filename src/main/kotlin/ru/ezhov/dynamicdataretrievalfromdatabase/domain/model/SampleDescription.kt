package ru.ezhov.dynamicdataretrievalfromdatabase.domain.model

import com.vladmihalcea.hibernate.type.json.JsonStringType
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import org.hibernate.annotations.Type

@Entity
class SampleDescription(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: String,
    var name: String,

    @Type(JsonStringType::class)
    var outputMapping: Map<String, String>,

    var query: String,
)