package com.example.wallpapers.data.implementation.exceptions

class AuthorizationErrorException: Exception {
    constructor() : super()
    constructor(message: String) : super(message)
}