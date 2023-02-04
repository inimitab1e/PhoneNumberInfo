package com.example.phonenumberinfo.data.network

import com.example.phonenumberinfo.data.modelDto.ResponseDto
import com.example.phonenumberinfo.domain.network_features.result.Result
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("v1/validate/+{number}")
    suspend fun getNumberInfo(
        @Path("number") number: String,
        @Query("apikey") api_key: String
    ): Result<ResponseDto>
}