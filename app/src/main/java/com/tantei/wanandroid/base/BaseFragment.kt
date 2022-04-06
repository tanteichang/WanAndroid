package com.tantei.wanandroid.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

import com.tantei.wanandroid.R
import java.lang.reflect.ParameterizedType

abstract class BaseFragment : Fragment() {
    protected abstract val layoutId: Int
    protected lateinit var mToolbar: androidx.appcompat.widget.Toolbar
    protected lateinit var mActivity: AppCompatActivity

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mActivity = activity as AppCompatActivity
        mToolbar = mActivity.findViewById(R.id.tool_bar)
        mActivity.setSupportActionBar(mToolbar)
        initView()
        initObserver()
    }
    abstract fun initObserver()
    abstract fun initView()
}