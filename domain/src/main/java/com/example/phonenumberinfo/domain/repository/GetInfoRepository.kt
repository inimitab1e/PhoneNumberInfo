package com.example.phonenumberinfo.domain.repository

import com.example.phonenumberinfo.domain.model.ResponseToShow

interface GetInfoRepository {
    suspend fun getInfo(number: String): ResponseToShow?
}