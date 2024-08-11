package com.example.domain.base.error

import java.io.IOException

class NetworkException(val code: CommonErrorCode) : IOException()