package com.zrz.android.pictureloading.feature.main

import android.graphics.Bitmap
import android.os.Bundle
import com.zrz.android.pictureloading.R
import com.zrz.android.pictureloading.core.base.BaseActivity
import com.zrz.android.pictureloading.entity.PaintingType.LANDSCAPE
import com.zrz.android.pictureloading.entity.PaintingType.SEASCAPE
import com.zrz.android.pictureloading.model.network.DownloadTask
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), DownloadTask.Callback {
    
    override fun obtainLayoutResID(): Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(obtainLayoutResID())
        var typeOfPainting = true
        btnLoad.setOnClickListener {
            val requestWidth = ivShowPicture.width
            val requestHeight = ivShowPicture.height
            val urlAddress = if (typeOfPainting) LANDSCAPE.urlAddress else SEASCAPE.urlAddress
            val downloadTask = downloader.download(urlAddress, this, requestWidth, requestHeight)
            downloadTask.execute()
            typeOfPainting = !typeOfPainting
        }
    }

    override fun callingBack(bitmap: Bitmap?) {
        ivShowPicture.setImageBitmap(bitmap)
    }
}
