package com.dfzt.mvpproject.api

import com.dfzt.mvpproject.bean.PhotoBean
import io.reactivex.Observable
import io.reactivex.Observer
import retrofit2.http.GET
import retrofit2.http.Path

interface PhotoApi {

    @GET("data/%E7%A6%8F%E5%88%A9/{pageSize}/{page}")
    fun getPhotos(@Path("page") page: Int,@Path("pageSize") pageSize: Int): Observable<PhotoBean>
}