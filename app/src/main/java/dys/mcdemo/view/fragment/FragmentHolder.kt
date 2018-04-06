package dys.mcdemo.view.fragment

import dys.mcdemo.R
import dys.mcdemo.databinding.FragmentHolderBinding
import dys.mcdemo.frame.base.BaseFragment

/**
 * Created by dys on 2018/4/6 0006.
 */
class FragmentHolder : BaseFragment<FragmentHolderBinding>() {
    private var origin = ""

    override fun getLayoutId(): Int {
        return R.layout.fragment_holder
    }

    companion object {
        fun newInstance(origin: String): FragmentHolder {
            val fragment = FragmentHolder()
            return fragment.apply {
                this.origin = origin
            }
        }
    }

    override fun initView() {

    }

    override fun initData() {
        mBinding.text.text = origin
    }

}