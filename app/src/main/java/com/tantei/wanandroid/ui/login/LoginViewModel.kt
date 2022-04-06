package com.tantei.wanandroid.ui.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.tantei.wanandroid.base.BaseViewModel
import com.tantei.wanandroid.network.ApiResult
import com.tantei.wanandroid.network.WanNetwork
import com.tantei.wanandroid.ui.mine.bean.User
import com.tantei.wanandroid.utils.LLog
import kotlinx.coroutines.launch

class LoginViewModel : BaseViewModel() {

    enum class LoginResult { SUCCESS, FAILURE, UNKUNOWN }

    val username = MutableLiveData<String>("")
    val password = MutableLiveData<String>("")
    val result = MutableLiveData(LoginResult.UNKUNOWN)
    var message = MutableLiveData("")
    lateinit var user: User

    fun doLogin() {
        message.value = ""
        viewModelScope.launch {
            when(val res = WanNetwork.doLogin(username.value!!, password.value!!)) {
                is ApiResult.Success -> {
                    LLog.d("$res")
                    user = res.result?.data!!
                    result.value = LoginResult.SUCCESS
                    message.value = "登录成功，3秒后跳转"

                }
                is ApiResult.Failure -> {
                    LLog.d("$res")
                    result.value = LoginResult.FAILURE
                    message.value = res.errorMessage
                }
            }
        }

    }
}