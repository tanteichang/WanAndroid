package com.tantei.wanandroid.ui.mine

import androidx.navigation.fragment.findNavController
import com.tantei.wanandroid.R
import com.tantei.wanandroid.base.BaseFragmentVMVB
import com.tantei.wanandroid.databinding.FragmentMineBinding
import com.tantei.wanandroid.ui.login.LoginFragmentArgs
import com.tantei.wanandroid.ui.web.WebFragmentArgs
import com.tantei.wanandroid.utils.LLog


class MineFragment : BaseFragmentVMVB<MineViewModel, FragmentMineBinding>() {
    override val layoutId: Int
        get() = R.layout.fragment_mine

    override fun initView() {
        mBinding.pageMineUserNickName.setOnClickListener {
            LLog.d("go to login")
            val bundle = LoginFragmentArgs(R.id.page_mine, R.id.page_mine).toBundle()
            findNavController().navigate(R.id.action_mine_to_login, bundle)
        }
    }
    override fun initObserver() {
        mViewModel.userInfo.observe(viewLifecycleOwner) {

        }
    }
}