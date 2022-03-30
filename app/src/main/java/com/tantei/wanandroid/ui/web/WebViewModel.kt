package com.tantei.wanandroid.ui.web

import androidx.lifecycle.MutableLiveData
import com.tantei.wanandroid.base.BaseViewModel

class WebViewModel : BaseViewModel() {
    val progress: MutableLiveData<Int> = MutableLiveData(0)
    val title: MutableLiveData<String> = MutableLiveData("")
}