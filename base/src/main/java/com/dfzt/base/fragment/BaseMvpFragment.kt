package com.dfzt.base.fragment

import android.os.Bundle
import android.view.View
import com.dfzt.base.mvp.BasePresenter
import com.dfzt.base.mvp.IBaseView

abstract class BaseMvpFragment<V: IBaseView,P: BasePresenter<V>> : BaseFragment() {


    protected var mPresenter: P? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mPresenter = initPresenter()

        mPresenter?.attachView(this as V)
        mPresenter?.setContext(mContext)
    }

    protected abstract fun initPresenter(): P?



    override fun onDestroy() {
        super.onDestroy()
        mPresenter?.detachView()
    }
}