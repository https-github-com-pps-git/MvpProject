package com.dfzt.base.activity

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import android.os.Build
import android.os.Bundle
import com.dfzt.base.mvp.BasePresenter
import com.dfzt.base.mvp.IBaseView

open abstract class BaseMvpActivity<V : IBaseView,P : BasePresenter<V>> : BaseActivity() {

    protected var mPresenter: P? = null
    val NET_ERROR = -1
    val NET_MOBILE = 1
    val NET_WIFI = 2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mPresenter = initPresenter()
        mPresenter?.setContext(mContext)
        mPresenter?.attachView(this as V)

        initNetWork()
        initNetData()
    }


    protected abstract fun initPresenter(): P?

    override fun onDestroy() {
        super.onDestroy()
        mPresenter?.detachView()
    }

    protected open fun initNetData(){

    }

    private fun initNetWork() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val connectivityManager =
                getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            connectivityManager?.requestNetwork(
                NetworkRequest.Builder().build(),
                object : ConnectivityManager.NetworkCallback() {
                    /**
                     * 网络可用的回调
                     */
                    override fun onAvailable(network: Network) {
                        super.onAvailable(network)
                        //  Log.e("PPS", "onAvailable");
                        //获取网络连接信息
                        val networkInfo = connectivityManager.activeNetworkInfo
                        if (networkInfo != null && networkInfo.isAvailable) {
                            if (networkInfo.type == ConnectivityManager.TYPE_WIFI) {
                                networkState(NET_WIFI)//"WIFI网络"
                            }
                            if (networkInfo.type == ConnectivityManager.TYPE_MOBILE) {
                                networkState(NET_MOBILE)//"手机数据网络"
                            }
                        }
                    }

                    /**
                     * 网络丢失的回调
                     */
                    override fun onLost(network: Network) {
                        super.onLost(network)
                        networkState(NET_ERROR)
                    }
                })
        }
    }

    protected abstract fun networkState(state: Int)
}