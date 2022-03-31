package com.tantei.wanandroid.network

import java.io.IOException

class ApiException(val errorCode: Int, val errorMessage: String): IOException()