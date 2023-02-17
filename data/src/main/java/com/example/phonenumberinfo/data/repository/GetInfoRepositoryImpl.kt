package com.example.phonenumberinfo.data.repository

import com.example.data.BuildConfig
import com.example.phonenumberinfo.data.network.ApiService
import com.example.phonenumberinfo.data.toResponseToShow
import com.example.phonenumberinfo.domain.model.ResponseToShow
import com.example.phonenumberinfo.domain.network_features.result.Result
import com.example.phonenumberinfo.domain.network_features.result.map
import com.example.phonenumberinfo.domain.repository.GetInfoRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetInfoRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val ioDispatcher: CoroutineDispatcher
) : GetInfoRepository {

    private val api_key: String = BuildConfig.api_key

    override suspend fun getInfo(number: String): Result<ResponseToShow?> {
        return withContext(ioDispatcher) {
            val response = apiService.getNumberInfo(number, api_key)
            val responseBody = response.map { value -> value.toResponseToShow() }
            return@withContext responseBody
        }
    }
}