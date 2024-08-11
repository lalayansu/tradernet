package com.example.domain.base.error

enum class CommonErrorCode(val code: Int) {
    NeedToShowErrorMessage(1),
    DoNotShowErrorMessage(2),

    NullPointerException(991),
    IllegalStateException(992),
    IllegalArgumentException(993),
    ArrayIndexOutOfBoundsException(994),
    Unknown(999),

    Continue(100),
    SwitchingProtocols(101),
    Processing(102),

    OK(200),
    Created(201),
    Accepted(202),

    BadRequest(400),
    Unauthorized(401),
    Forbidden(403),
    NotFound(404),
    MethodNotAllowed(405),
    NotAcceptable(406),
    RequestTimeout(408),

    InternalServerError(500),
    NotImplemented(501),
    BadGateway(502),
    ServiceUnavailable(503),
    GatewayTimeout(504),

    NoNetwork(600);

    companion object {
        fun from(code: Int) = entries.find { it.code == code } ?: Unknown
    }
}
