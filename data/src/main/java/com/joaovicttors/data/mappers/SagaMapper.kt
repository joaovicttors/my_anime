package com.joaovicttors.data.mappers

import com.joaovicttors.domain.entities.Saga
import com.joaovicttors.infrastructure.models.SagaModel

object SagaMapper {
    fun modelToEntity(model: SagaModel) = Saga(
        model.titles,
        model.descriptions,
        model.episodeFrom,
        model.episodeTo,
        model.episodeCount
    )
}