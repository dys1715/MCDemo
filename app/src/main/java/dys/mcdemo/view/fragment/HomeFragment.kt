package dys.mcdemo.view.fragment

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import dys.mcdemo.R
import dys.mcdemo.databinding.FragmentHomeBinding
import dys.mcdemo.frame.base.BaseFragment
import dys.mcdemo.model.HomeModel
import dys.mcdemo.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.fragment_home.*


/**
 * Created by dys on 2018/4/6 0006.
 *
 */
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    var mIsRefreshing = false

    override fun getLayoutId(): Int = R.layout.fragment_home

    companion object {
        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }

    override fun initView() {
        mBinding.model = HomeModel()
        mBinding.viewModel = HomeViewModel(this, mBinding.model!!)
        //refresh
        initSwipeRefreshLayout()
        //list
        initRecyclerView()
    }

    override fun initData() {
        initHomeData()
    }

    private fun initHomeData() {
        mBinding.viewModel!!.getHomeData()
    }

    private fun initSwipeRefreshLayout() {
        swipe_refresh_layout.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary)
        swipe_refresh_layout.setOnRefreshListener({
            if (!mIsRefreshing) {
                mIsRefreshing = true
                initHomeData()
            }
        })
    }

    private fun initRecyclerView() {
        val llm = LinearLayoutManager(activity)
        recyclerview.layoutManager = llm

        recyclerview.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            @Suppress("DEPRECATED_IDENTITY_EQUALS")
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val topRowVerticalPosition =
                        if (recyclerView == null || recyclerView.childCount === 0) 0
                        else recyclerView.getChildAt(0).top
                swipe_refresh_layout.isEnabled = topRowVerticalPosition >= 0
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE &&
                        llm.findLastVisibleItemPosition() >= mBinding.viewModel!!.getAdapter().itemCount - 1) {
                    //load more data
//                    initHomeData()
                }
            }
        })
        recyclerview.adapter = mBinding.viewModel!!.getAdapter()
    }

    override fun onPause() {
        super.onPause()
        swipe_refresh_layout.isRefreshing = false
    }
}
