package com.dfzt.mvpproject.ui.photoActivity

import androidx.recyclerview.widget.GridLayoutManager
import com.dfzt.base.activity.BaseMvpActivity
import com.dfzt.common.util.LogUtil
import com.dfzt.mvpproject.R
import com.dfzt.mvpproject.adapter.PhotoAdapter
import com.dfzt.mvpproject.bean.PhotoBean
import com.dfzt.mvpproject.bean.Result
import kotlinx.android.synthetic.main.activity_photo.*

class PhotoActivity : BaseMvpActivity<PhotoContract.PhotoActivityView,PhotoPresenterImpl>(),PhotoContract.PhotoActivityView {

    private var mAdapter: PhotoAdapter? = null
    private var mList = mutableListOf<Result>()
    override fun getLayoutId(): Int {
        return R.layout.activity_photo
    }

    override fun initPresenter(): PhotoPresenterImpl? {
        return PhotoPresenterImpl()
    }


    override fun initNetData() {
        super.initNetData()
        initAdapter()
        mPresenter?.getPhotos(15,20)
    }

    private fun initAdapter() {
        mAdapter = PhotoAdapter(mContext)
        mAdapter?.setList(mList)
        recyclerview.layoutManager = GridLayoutManager(mContext,2)
        recyclerview.adapter = mAdapter
    }

    override fun networkState(state: Int) {

    }

    override fun start() {
        LogUtil.e("开始获取数据")
    }

    override fun onFinish() {
        LogUtil.e("获取数据完成")
    }

    override fun onRefreshFailure(msg: String?) {
        LogUtil.e("获取数据失败 $msg")
    }

    override fun getDataSuccess(mPhotoBean: PhotoBean?) {
        LogUtil.e("获取数据成功" + mPhotoBean?.results?.size)
        mList.clear()
        mList.addAll(mPhotoBean?.results!!)
        mAdapter?.setList(mList)
    }


}
