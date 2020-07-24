package com.dfzt.base.mvp

interface IBaseModelCallback<T> {

    fun getDataSuccess(t: T)

    fun getDataFailed(msg: String?)

    fun onFinish()
}