package com.example.phonenumberinfo.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isGone
import androidx.core.view.isVisible
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.phonenumberinfo.R
import com.example.phonenumberinfo.databinding.ActivityMainBinding
import com.example.phonenumberinfo.domain.model.ResponseToShow
import com.example.phonenumberinfo.presentation.utils.NetworkConnection
import com.example.phonenumberinfo.presentation.utils.StringConstants
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val binding by viewBinding(ActivityMainBinding::bind)
    private val mainActivityViewModel: MainActivityViewModel by viewModels()
    private val connectivity: NetworkConnection by lazy {
        NetworkConnection(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        checkConnectivity()

        mainActivityViewModel.numberInfoResponse.observe(this) { response ->
            if (response != null) {
                successfulResponseUiSetup(response)
            } else {
                failedResponseUiSetup()
            }
        }
    }

    private fun checkConnectivity() {
        connectivity.observe(this) { isConnected ->
            if (!isConnected) {
                with(binding) {
                    btnGetInfo.isVisible = false
                    badNetworkConnectionPage.isVisible = true
                }
            } else {
                with(binding) {
                    btnGetInfo.isVisible = true
                    badNetworkConnectionPage.isGone = true
                }
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
                tvCountryNameValue.text = StringConstants.notFound
                tvLocationValue.text = StringConstants.notFound
                tvCarierValue.text = StringConstants.notFound
            }
        }
    }

    private fun failedResponseUiSetup() {
        with(binding) {
            tvNumberValue.text = StringConstants.notFound
            tvCountryNameValue.text = StringConstants.notFound
            tvLocationValue.text = StringConstants.notFound
            tvCarierValue.text = StringConstants.notFound
        }
    }
}