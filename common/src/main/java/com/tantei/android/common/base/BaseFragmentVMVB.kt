package com.tantei.android.common.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType

abstract class BaseFragmentVMVB<VM: ViewModel, VB: ViewBinding> : BaseFragmentVB<VB>() {
    protected lateinit var mViewModel: VM

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel = ViewModelProvider(this).get(getVmClazz(this))
    }

    private fun <VM> getVmClazz(obj: Any): VM {
        return (obj.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as VM
    }
}