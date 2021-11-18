package com.example.scareme3.model

data class ResponseToken(
    var accessToken: String,
    var accessTokenExpiredAt: String,
    var refreshToken: String,
    var refreshTokenExpiredAt: String
)
