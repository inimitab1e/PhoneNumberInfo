package com.example.phonenumberinfo.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.phonenumberinfo.R
import com.example.phonenumberinfo.databinding.ActivityMainBinding
import com.example.phonenumberinfo.domain.model.ResponseToShow
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val binding by viewBinding(ActivityMainBinding::bind)
    private val mainActivityViewModel: MainActivityViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainActivityViewModel.numberInfoResponse.observe(this) { response ->
            if (response != null) {
                successfulResponseUiSetup(response)
            } else {
                failedResponseUiSetup()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        binding.btnGetInfo.setOnClickListener {
            handleClickRequest()
        }
    }

    private fun handleClickRequest() {
        val number = binding.editText.text.toString()
        mainActivityViewModel.getNumberInfo(number)
    }

    private fun successfulResponseUiSetup(response: ResponseToShow) {
        with(binding) {
            if(response.country_name.isNotEmpty()) {
                tvNumberValue.text = response.number
                tvCountryNameValue.text = response.country_name
                tvLocationValue.text = response.location
                tvCarierValue.text = response.carrier
            } else {
                tvNumberValue.text = response.number
                tvCountryNameValue.text = "Не найдено"
                tvLocationValue.text = "Не найдено"
                tvCarierValue.text = "Не найдено"
            }
        }
    }

    private fun failedResponseUiSetup() {
        with(binding) {
            tvNumberValue.text = "Не найдено"
            tvCountryNameValue.text = "Не найдено"
            tvLocationValue.text = "Не найдено"
            tvCarierValue.text = "Не найдено"
        }
    }
}