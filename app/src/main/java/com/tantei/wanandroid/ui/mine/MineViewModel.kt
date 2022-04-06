package com.tantei.wanandroid.ui.mine

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tantei.wanandroid.base.BaseViewModel
import com.tantei.wanandroid.ui.mine.bean.User

class MineViewModel : BaseViewModel() {
    private val _userInfoLiveData = MutableLiveData<User>()
    val userInfo: LiveData<User>
        get() = _userInfoLiveData
}