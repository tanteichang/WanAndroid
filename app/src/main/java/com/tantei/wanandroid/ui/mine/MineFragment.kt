package com.tantei.wanandroid.ui.mine

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.navigation.fragment.findNavController
import com.tantei.wanandroid.R
import com.tantei.wanandroid.base.BaseFragmentVMVB
import com.tantei.wanandroid.databinding.FragmentMineBinding
import com.tantei.wanandroid.ui.login.LoginFragmentArgs
import com.tantei.wanandroid.utils.LLog


class MineFragment : BaseFragmentVMVB<MineViewModel, FragmentMineBinding>() {
    override val layoutId: Int
        get() = R.layout.fragment_mine

    override fun initView() {
        mBinding.pageMineBtnLogin.setOnClickListener {
            LLog.d("go to login")
               if (mViewModel.hasLoginLiveData.value == false) {
                   val bundle = LoginFragmentArgs(R.id.page_mine, R.id.page_mine).toBundle()
                   findNavController().navigate(R.id.action_mine_to_login, bundle)
               }
        }
        mViewModel.checkLoginState()
        mViewModel.getUserInfo()
    }
    override fun initObserver() {
        mViewModel.userInfoLiveData.observe(viewLifecycleOwner) {
            LLog.d("$it")
            mBinding.pageMineBtnLogin.text = it.userInfo.nickname
            mBinding.pageMineCoin.text = it.coinInfo.coinCount.toString()
            mBinding.pageMinLevel.text = "Lv ${it.coinInfo.level}"
            mBinding.pageMineRank.text = it.coinInfo.rank.toString()
        }
        mViewModel.hasLoginLiveData.observe(viewLifecycleOwner) {
           mBinding.pageMineBtnLogin.visibility = if (it) View.VISIBLE else View.GONE
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.mine_tool_bar, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when(item.itemId) {
        R.id.tool_setting -> {
            findNavController().navigate(R.id.action_mine_to_setting)
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }

}