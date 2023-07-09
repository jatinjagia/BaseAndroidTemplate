package com.baseandroidtemplate.network

data class ErrorResponse(
    val code : String,
    val reasons : String,
    val errorCode : String
)