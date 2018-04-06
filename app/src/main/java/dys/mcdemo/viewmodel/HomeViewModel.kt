package dys.mcdemo.viewmodel

import android.databinding.Bindable
import android.util.Log
import dys.mcdemo.BR
import dys.mcdemo.adapter.HomeItem
import dys.mcdemo.frame.base.BaseViewModel
import dys.mcdemo.frame.base.MultiTypeAdapter
import dys.mcdemo.frame.bean.HomeBean
import dys.mcdemo.frame.network.Consumer
import dys.mcdemo.model.HomeModel
import dys.mcdemo.view.fragment.HomeFragment
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.*

/**
 * Created by dys on 2018/4/6 0006.
 *
 */
class HomeViewModel() : BaseViewModel() {
    private var mFragment: HomeFragment? = null
    private var model: HomeModel? = null
    private val adapter = MultiTypeAdapter()

    var mList = ArrayList<HomeBean.IssueListBean.ItemListBean>()

    @Bindable
    var homeBean: HomeBean? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.homeBean)
        }

    constructor(mFragment: HomeFragment, model: HomeModel) : this() {
        this.mFragment = mFragment
        this.model = model
    }

    fun getHomeData() {
        mFragment!!.swipe_refresh_layout.isRefreshing = true
        model!!.getHomeData(object : Consumer<HomeBean> {
            override fun call(t: HomeBean) {
                mList.clear()
                t.issueList!!
                        .flatMap { it.itemList!! }
                        .filter { it.type.equals("video") }
                        .forEach { mList.add(it) }
                initItem()
                mFragment!!.swipe_refresh_layout.isRefreshing = false

            }
        })
    }

    fun getAdapter(): MultiTypeAdapter = adapter

    private fun initItem() {
        adapter.clearItems()
        for (i in mList.indices) {
            Log.e(">>>>>", mList[i].toString())
            adapter.addItem(HomeItem(mFragment!!.activity, mList[i]))
        }
        adapter.notifyDataSetChanged()
    }

}