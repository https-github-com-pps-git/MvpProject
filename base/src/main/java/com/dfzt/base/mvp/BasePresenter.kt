package com.dfzt.base.mvp

import android.content.Context
import android.util.Log

open class BasePresenter<V : IBaseView> {

    protected var mContext: Context? = null
    protected var mView: V? = null

    fun attachView(mView: V){
        this.mView = mView
    }

    fun setContext(mContext: Context){
        this.mContext = mContext
    }

    open fun detachView(){
        Log.e("PPS","  detachView ")
        mView = null
        mContext = null
    }

}