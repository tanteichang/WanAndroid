package com.tantei.wanandroid.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tantei.wanandroid.base.BaseViewModel

private const val TAG = "GlobalViewModel"

class GlobalViewModel : BaseViewModel() {
    private val mutableBottomNavigationHide = MutableLiveData<Boolean>()
    val bottomNavigationHide: LiveData<Boolean>
        get() = mutableBottomNavigationHide
    fun setBottomNavigationHide(value: Boolean) {
        Log.d(TAG, "setBottomNavigationHide: $value")
        mutableBottomNavigationHide.value = value
    }
}