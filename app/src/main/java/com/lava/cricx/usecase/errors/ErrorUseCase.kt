package com.lava.cricx.usecase.errors

import com.lava.cricx.data.error.Error

interface ErrorUseCase {
    fun getError(errorCode: Int): Error
}
