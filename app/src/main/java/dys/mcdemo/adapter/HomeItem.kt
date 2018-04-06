package dys.mcdemo.adapter

import android.app.Activity
import android.content.Context
import android.view.View
import android.widget.Toast
import dys.mcdemo.R
import dys.mcdemo.frame.base.BaseItem
import dys.mcdemo.frame.bean.HomeBean
import dys.mcdemo.frame.newIntent
import dys.mcdemo.frame.showToast
import dys.mcdemo.view.TestActivity

/**
 * Created by dys on 2018/4/6 0006.
 */
class HomeItem() : BaseItem() {
    var bean: HomeBean.IssueListBean.ItemListBean? = null
    var imgUrl: String? = null
    var userIcon: String? = null
    var title: String = ""
    var detail: String = ""

    override val layout: Int = R.layout.item_home

    constructor(context: Activity, bean: HomeBean.IssueListBean.ItemListBean) : this() {
        this.bean = bean
        imgUrl = bean.data!!.cover!!.feed
        userIcon = bean.data!!.author!!.icon
        title = bean.data!!.title!!
        val category = bean.data?.category
        val minutes = bean.data?.duration?.div(60)
        val realMin = if (minutes!! < 10) {
            "0$minutes"
        } else {
            minutes.toString()
        }
        val second = bean.data?.duration?.minus(minutes.times(60))
        val realSec = if (second!! < 10) {
            "0$second"
        } else {
            second.toString()
        }
        detail = "$category / $realMin:$realSec"

        setOnClickListener(View.OnClickListener {
            when (it.id) {
                R.id.rl_home_root -> {
                    context.newIntent<TestActivity>()
                }
            }
        })

    }
}