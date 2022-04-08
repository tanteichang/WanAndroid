package com.tantei.wanandroid.ui.login

import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.tantei.wanandroid.R
import com.tantei.wanandroid.base.BaseFragment
import com.tantei.wanandroid.base.BaseFragmentVMVB
import com.tantei.wanandroid.databinding.FragmentLoginBinding
import com.tantei.wanandroid.network.WanNetwork
import com.tantei.wanandroid.utils.LLog
import com.tantei.wanandroid.viewmodels.GlobalViewModel
import java.util.*


class LoginFragment : BaseFragmentVMVB<LoginViewModel, FragmentLoginBinding>()  {
    override val layoutId: Int
        get() = R.layout.fragment_login
    override fun initObserver() {
        mViewModel.result.observe(viewLifecycleOwner) {
            LLog.d("result obs $it")
            when(it) {
                LoginViewModel.LoginResult.FAILURE -> {
                    LLog.d("will make test, msg is ${mViewModel.message}")
                    mBinding.pageLoginResult.setTextColor(resources.getColor(R.color.colorError))
                }
                LoginViewModel.LoginResult.SUCCESS -> {
                    LLog.d("login succsss ${mViewModel.user}")
                    mBinding.pageLoginResult.setTextColor(resources.getColor(R.color.colorPrimary))
                    Handler().postDelayed({ findNavController().navigate(R.id.action_login_to_mine) }, 1000 * 3)
                }
            }
        }
        mViewModel.message.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                mBinding.pageLoginResult.text = it.toString()
            }
        }
    }

    override fun initView() {

        mBinding.password.addTextChangedListener {
            mViewModel.password.value = it.toString()
        }
        mBinding.username.addTextChangedListener {
            mViewModel.username.value = it.toString()
        }
        mBinding.login.setOnClickListener {
            mViewModel.message.value = ""
            mViewModel.doLogin()
        }
    }

}