package com.example.phonenumberinfo.presentation.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.phonenumberinfo.domain.model.ResponseToShow
import com.example.phonenumberinfo.domain.repository.GetInfoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.phonenumberinfo.domain.network_features.result.Result

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val repo: GetInfoRepository
) : ViewModel() {

    private var _numberInfoResponse = MutableLiveData<ResponseToShow?>()
    val numberInfoResponse: LiveData<ResponseToShow?> get() = _numberInfoResponse

    private var _errorResponse = MutableLiveData<String>()
    val errorResponse: LiveData<String> get() = _errorResponse

    fun getNumberInfo(number: String) {
        viewModelScope.launch {
            val response = repo.getInfo(number)
            when(response) {
                is Result.Success -> _numberInfoResponse.postValue(response.value)
                is Result.Failure<*> -> _errorResponse.postValue(response.error?.message)
            }
        }
    }
}