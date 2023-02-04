package com.example.phonenumberinfo.domain.model

data class ResponseToShow(
    val carrier: String,
    val country_name: String,
    val location: String,
    val number: String,
)
