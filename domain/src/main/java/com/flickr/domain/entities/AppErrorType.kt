package com.flickr.domain.entities

sealed class ErrorType {
    abstract val id: Int
    abstract val title: String
    abstract val description: String

    object GenericError : ErrorType() {
        override val id: Int = 0
        override val title: String = "Generic error title"
        override val description: String = "Generic error description"
    }
}
