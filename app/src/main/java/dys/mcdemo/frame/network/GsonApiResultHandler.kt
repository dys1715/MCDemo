package dys.mcdemo.frame.network

import android.content.Context

import io.reactivex.Observer
import io.reactivex.disposables.Disposable


/**
 * Created by dys on 2018/4/6 0006.
 */

abstract class GsonApiResultHandler<T> : Observer<T> {
    override fun onSubscribe(d: Disposable?) {

    }

    override fun onComplete() {

    }

    override fun onError(e: Throwable) {
        e.printStackTrace()
    }

    override fun onNext(responseData: T) {
        onSuccess(responseData)
    }

    abstract fun onSuccess(responseData: T)

}
