package dys.mcdemo.model

import dys.mcdemo.frame.applySchedulers
import dys.mcdemo.frame.bean.FindBean
import dys.mcdemo.frame.bean.HomeBean
import dys.mcdemo.frame.network.A
import dys.mcdemo.frame.network.API
import dys.mcdemo.frame.network.Consumer
import dys.mcdemo.frame.network.GsonApiResultHandler

/**
 * Created by dys on 2018/4/6 0006.
 */
class HomeModel {
    private val api = A.get(API::class.java)

    fun getHomeData(consumer: Consumer<HomeBean>) {
        api.getHomeData().applySchedulers()
                .subscribe(object : GsonApiResultHandler<HomeBean>() {
                    override fun onSuccess(responseData: HomeBean) {
                        consumer.call(responseData)
                    }
                })
    }

    fun getFindData(consumer: Consumer<MutableList<FindBean>>) {
        api.getFindData().applySchedulers()
                .subscribe(object : GsonApiResultHandler<MutableList<FindBean>>() {
                    override fun onSuccess(responseData: MutableList<FindBean>) {
                        consumer.call(responseData)
                    }
                })
    }
}