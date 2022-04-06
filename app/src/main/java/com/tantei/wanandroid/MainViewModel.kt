package com.tantei.wanandroid

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tantei.wanandroid.base.BaseViewModel

class MainViewModel : BaseViewModel() {
    val isBottomNavHide = MutableLiveData<Boolean>(false)
    val toolbarTitle = MutableLiveData<String>("")

    private val toastMessageListData = MutableLiveData<String>()
    val toastMessage: LiveData<String>
        get() = toastMessageListData
    fun showToast(message: String) {
        toastMessageListData.value = message
    }
}