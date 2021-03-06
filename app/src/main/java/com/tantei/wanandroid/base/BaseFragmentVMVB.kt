package com.tantei.wanandroid.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelLazy
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.tantei.wanandroid.MainActivity
import com.tantei.wanandroid.MainViewModel
import com.tantei.wanandroid.R
import java.lang.reflect.ParameterizedType

abstract class BaseFragmentVMVB<VM: ViewModel, VB: ViewBinding> : Fragment() {
    lateinit var mViewModel: VM
    lateinit var mBinding: VB
    protected abstract val layoutId: Int
    protected lateinit var mToolbar: androidx.appcompat.widget.Toolbar
    protected lateinit var mActivity: AppCompatActivity
    protected val globalViewModel by lazy { ViewModelLazy(MainViewModel::class, { MainActivity.myViewModelStore!!}, {defaultViewModelProviderFactory}).value }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel = ViewModelProvider(this).get(getVmClazz(this))
        mActivity = activity as AppCompatActivity
        mToolbar = mActivity.findViewById(R.id.tool_bar)
        mActivity.setSupportActionBar(mToolbar)
        initView()
        initObserver()


    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val type = javaClass.genericSuperclass as ParameterizedType
        val aClass = type.actualTypeArguments[1] as Class<*>
        val method = aClass.getDeclaredMethod("inflate", LayoutInflater::class.java, ViewGroup::class.java, Boolean::class.java)
        mBinding = method.invoke(null, layoutInflater, container, false) as VB
        return mBinding.root
    }

    private fun <VM> getVmClazz(obj: Any): VM {
        return (obj.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as VM
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    abstract fun initObserver()
    abstract fun initView()
}