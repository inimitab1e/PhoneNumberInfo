package com.example.phonenumberinfo.data

import com.example.phonenumberinfo.data.modelDto.ResponseDto
import com.example.phonenumberinfo.domain.model.ResponseToShow

fun ResponseDto.toResponseToShow() = ResponseToShow(
    number = number,
    country_name = country_name,
    location = location,
    carrier = carrier
)