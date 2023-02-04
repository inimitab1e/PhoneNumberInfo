package com.example.phonenumberinfo.domain.repository

import com.example.phonenumberinfo.domain.model.ResponseToShow
import com.example.phonenumberinfo.domain.network_features.result.Result

interface GetInfoRepository {
    suspend fun getInfo(number: String): Result<ResponseToShow?>
}