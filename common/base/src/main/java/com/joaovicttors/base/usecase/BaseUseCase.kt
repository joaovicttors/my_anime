package com.joaovicttors.base.usecase

import com.joaovicttors.base.Response

abstract class BaseUseCase<In, Out> {

    abstract suspend operator fun invoke(param: In): Response<Out>
}