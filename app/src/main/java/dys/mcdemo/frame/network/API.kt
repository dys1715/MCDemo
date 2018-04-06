package dys.mcdemo.frame.network

import dys.mcdemo.frame.bean.FindBean
import dys.mcdemo.frame.bean.HomeBean
import io.reactivex.Observable
import retrofit2.http.GET


/**
 * Created by dys on 2018/4/5 0005.
 */
interface API{
    //获取首页第一页数据
    @GET("v2/feed?num=2&udid=26868b32e808498db32fd51fb422d00175e179df&vc=83")
    fun getHomeData(): Observable<HomeBean>

    //获取发现频道信息
    @GET("v2/categories?udid=26868b32e808498db32fd51fb422d00175e179df&vc=83")
    fun getFindData() : Observable<MutableList<FindBean>>
}