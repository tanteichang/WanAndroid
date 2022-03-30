package com.tantei.wanandroid

import androidx.lifecycle.MutableLiveData
import com.tantei.wanandroid.base.BaseViewModel

class MainViewModel : BaseViewModel() {
    val isBottomNavHide = MutableLiveData<Boolean>(false)
    val toolbarTitle = MutableLiveData<String>("")
}