package dys.mcdemo.frame.network

/**
 * Created by dys on 2018/4/6 0006.
 */
interface Consumer<in T> {
    fun call(t: T)
}