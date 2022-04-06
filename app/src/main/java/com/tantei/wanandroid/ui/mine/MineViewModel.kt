package com.tantei.wanandroid.ui.mine

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.tantei.wanandroid.WanAndroidApplication
import com.tantei.wanandroid.base.BaseViewModel
import com.tantei.wanandroid.network.Api
import com.tantei.wanandroid.network.ApiResult
import com.tantei.wanandroid.repositories.UserRepository
import com.tantei.wanandroid.ui.mine.bean.User
import com.tantei.wanandroid.ui.mine.bean.UserInfoRes
import com.tantei.wanandroid.utils.LLog
import kotlinx.coroutines.launch
import javax.inject.Inject

class MineViewModel : BaseViewModel() {

     val userInfoLiveData = MutableLiveData<UserInfoRes>()


    val hasLoginLiveData = MutableLiveData<Boolean>(false)

    fun checkLoginState() {
        val sharedPreferences = WanAndroidApplication.context.getSharedPreferences("cookieData", Context.MODE_PRIVATE)
        val hasLogin = sharedPreferences.getBoolean("hasLogin", false)
        hasLoginLiveData.value = hasLogin
    }

    fun getUserInfo() {
        LLog.d("call geruserINfo")
        viewModelScope.launch {
            when(val res = UserRepository.getUserInfo()) {
                is ApiResult.Success -> {
                    userInfoLiveData.value = res.result?.data!!
                }
                is ApiResult.Failure -> {
                    LLog.d("Failure $res")
                }
            }
        }
    }

}