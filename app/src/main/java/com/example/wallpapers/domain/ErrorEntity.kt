package com.example.wallpapers.domain

sealed class ErrorEntity {

    object NetworkFailure: ErrorEntity()

    object ServerError: ErrorEntity()
}
