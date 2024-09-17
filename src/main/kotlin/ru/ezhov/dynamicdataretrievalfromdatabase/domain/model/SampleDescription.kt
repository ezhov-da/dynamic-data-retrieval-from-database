package ru.ezhov.dynamicdataretrievalfromdatabase.domain.model

class SampleDescription(
    var name: String,

    var outputMapping: Map<String, String>,

    var query: String,
)