package com.example.phonenumberinfo.data.repository

import com.example.data.BuildConfig
import com.example.phonenumberinfo.data.network.ApiService
import com.example.phonenumberinfo.data.toResponseToShow
import com.example.phonenumberinfo.data.utils.AppDispatchers
import com.example.phonenumberinfo.domain.model.ResponseToShow
import com.example.phonenumberinfo.domain.repository.GetInfoRepository
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetInfoRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val dispatchers: AppDispatchers,
    private val api_key: String = BuildConfig.api_key
) : GetInfoRepository {

    override suspend fun getInfo(number: String): ResponseToShow? {
        return withContext(dispatchers.io) {
            val response = apiService.getNumberInfo(number, api_key)
            if (response.isSuccessful) {
                val responseBody = response.body() ?: return@withContext null
                return@withContext responseBody.toResponseToShow()
            } else {
                return@withContext null
            }
        }
    }
}