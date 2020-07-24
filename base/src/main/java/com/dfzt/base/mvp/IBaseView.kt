package com.dfzt.base.mvp

/**
 * 这个就是抽取的公共的方法
 */
open interface IBaseView {

    //正在加载
    fun start()

    //加载成功 可以显示数据了
    fun onFinish()

    //加载失败
    fun onRefreshFailure(msg: String?)

}