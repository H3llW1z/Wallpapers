package com.example.wallpapers.data.implementation.exceptions

class ClientErrorException: Exception {
    constructor() : super()
    constructor(message: String) : super(message)
}