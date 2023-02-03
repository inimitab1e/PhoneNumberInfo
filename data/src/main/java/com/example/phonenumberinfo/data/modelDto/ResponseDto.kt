package com.example.phonenumberinfo.data.modelDto

import com.google.gson.annotations.SerializedName

@kotlinx.serialization.Serializable
data class ResponseDto(
    @SerializedName("carrier")
    val carrier: String,
    @SerializedName("country_name")
    val country_name: String,
    @SerializedName("location")
    val location: String,
    @SerializedName("number")
    val number: String
)