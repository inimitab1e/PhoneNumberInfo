package com.example.phonenumberinfo.presentation.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.phonenumberinfo.domain.model.ResponseToShow
import com.example.phonenumberinfo.domain.repository.GetInfoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val repo: GetInfoRepository
) : ViewModel() {

    private var _numberInfoResponse = MutableLiveData<ResponseToShow?>()
    val numberInfoResponse get() = _numberInfoResponse

    fun getNumberInfo(number: String) {
        viewModelScope.launch {
            val response = repo.getInfo(number)
            numberInfoResponse.postValue(response)
        }
    }
}