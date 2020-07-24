package com.dfzt.base.activity

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.dfzt.base.R
import com.dfzt.base.util.StatusBarUtil

open abstract class BaseActivity : AppCompatActivity() {

    protected lateinit var mContext: Context
    protected lateinit var mActivity: Activity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        mActivity = this
        mContext = this
        initStatusBar()
    }

    //设置布局文件
    @LayoutRes
    protected abstract fun getLayoutId(): Int

    //设置沉浸式状态栏的颜色  如果你要换颜色 就重写这个方法 把supper去掉自己实现
    protected open fun initStatusBar(){
        StatusBarUtil.setColor(mActivity,resources.getColor(R.color.colorAccent),0)
    }

    //这个是提供的模板方法
    protected fun initData(){

    }

    //toast的方法
    fun toast(msg: String){
        Toast.makeText(mContext,msg,Toast.LENGTH_LONG).show()
    }



}