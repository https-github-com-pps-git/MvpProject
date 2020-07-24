package com.dfzt.network.base

import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.ObservableTransformer
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/***
 * 网络请求采用Okhttp + Rxjava + Retrofit 来实现
 *
 *
 */
open class BaseNetWorkUtil {

    private var mBaseUrl = ""

    protected constructor(mBaseUrl: String){
        this.mBaseUrl = mBaseUrl
    }
    //这个是Retrofit中需要的一个baseUrl
    private var mOkHttpClient: OkHttpClient? = null
    //定义一个Map集合用来缓存Retorfit
    private var mCacheRetrofit = HashMap<String,Retrofit>()

    //获取Retrofit对象
    protected fun getRetrofit(cls: Class<*>): Retrofit? {
        //1.先判断缓存中是否存在这个数据
        var mRetrofit: Retrofit? = mCacheRetrofit.get(mBaseUrl + cls.name)
        if (mRetrofit == null) {
            mRetrofit = Retrofit.Builder()
                .baseUrl(mBaseUrl)
                .client(getOkHttpClient())
                //设置GSON解析
                .addConverterFactory(GsonConverterFactory.create())
                //设置返回的适配器
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        }
        return mRetrofit
    }

    private fun getOkHttpClient(): OkHttpClient? {
        if (mOkHttpClient == null){

            val builder = OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(45, TimeUnit.SECONDS)
                .writeTimeout(55, TimeUnit.SECONDS)
            //添加拦截器
            if (getInterceptor() != null) {
                builder.addInterceptor(getInterceptor())
            }
            if (getBackInterceptor() != null) {
                builder.addInterceptor(getBackInterceptor())
            }
            mOkHttpClient = builder.build()
        }
        return mOkHttpClient
    }


    //定义一个抽象方法去获取拦截器(请求的拦截器)
    protected fun getInterceptor(): Interceptor? {
        return null
    }

    //获取返回的拦截器
    protected fun getBackInterceptor(): Interceptor? {
        return null
    }

    companion object {
        //线程切换
        fun <T> applySchedulers(observer: Observer<T>): ObservableTransformer<T, T> {
            return ObservableTransformer { upstream ->
                val observable = upstream.subscribeOn(Schedulers.io())//给上面的代码分配线程
                    .observeOn(AndroidSchedulers.mainThread())//给下面的代码分配线程
                observable.subscribe(observer)
                observable
            }
        }
    }

}