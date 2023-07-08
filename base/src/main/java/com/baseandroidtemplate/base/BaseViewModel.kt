package com.baseandroidtemplate.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

abstract class BaseViewModel(application: Application) : AndroidViewModel(application){

    private val _showToast = MutableLiveData<String>()
    val showToast : LiveData<String>
        get() = _showToast

    private val _showInternetError = MutableLiveData<Boolean>()
    val showInternetError : LiveData<Boolean>
        get() = _showInternetError
}