package com.dfzt.mvpproject.ui.photoActivity

import com.dfzt.base.mvp.IBaseModelCallback
import com.dfzt.mvpproject.api.PhotoApi
import com.dfzt.mvpproject.api.PhotoNetworUtil
import com.dfzt.mvpproject.bean.PhotoBean
import com.dfzt.network.base.BaseNetWorkUtil
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

class PhotoModelImpl : PhotoContract.PhotoModel {
    private var mDisposable: Disposable? = null
    override fun getPhotos(
        page: Int,
        pageIndex: Int,
        mListener: IBaseModelCallback<PhotoBean>
    ) :Disposable?{

        PhotoNetworUtil.getInstance()!!.getServiceApi(PhotoApi::class.java)!!
            .getPhotos(page,pageIndex).compose(BaseNetWorkUtil
                .applySchedulers(object : Observer<PhotoBean>{
                    override fun onComplete() {
                        mListener.onFinish()
                    }

                    override fun onSubscribe(d: Disposable?) {
                        mDisposable = d
                    }

                    override fun onNext(value: PhotoBean?) {
                        mListener.getDataSuccess(value!!)
                    }

                    override fun onError(e: Throwable?) {
                        mListener.getDataFailed(e?.message)
                    }

                }))

        return mDisposable

    }


}