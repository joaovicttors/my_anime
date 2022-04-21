package com.joaovicttors.domain.entities

data class Saga(
    val titles: Map<String, String>,
    val descriptions: Map<String, String>,
    val episodeFrom: Int,
    val episodeTo: Int,
    val episodeCount: Int,
)