package com.tantei.android.common.base

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {
    protected abstract val layoutId: Int
    protected lateinit var mActivity: AppCompatActivity

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mActivity = activity as AppCompatActivity
        initView()
        initObserver()
    }
    abstract fun initObserver()
    abstract fun initView()
}