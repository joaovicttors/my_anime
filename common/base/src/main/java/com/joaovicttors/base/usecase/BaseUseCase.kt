package com.joaovicttors.base.usecase

abstract class BaseUseCase<In, Out> {

    abstract suspend operator fun invoke(param: In): Out
}