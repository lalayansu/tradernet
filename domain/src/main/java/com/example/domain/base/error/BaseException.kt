package com.example.domain.base.error

import java.io.IOException

class BaseException(
    val extraErrorCode: CommonErrorCode,
    override val message: String? = null
) : IOException()
