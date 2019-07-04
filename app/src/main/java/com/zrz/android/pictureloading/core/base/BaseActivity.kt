package com.zrz.android.pictureloading.core.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.zrz.android.pictureloading.core.App
import com.zrz.android.pictureloading.model.network.Download

abstract class BaseActivity : AppCompatActivity() {

    protected lateinit var downloader: Download
    abstract fun obtainLayoutResID(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(obtainLayoutResID())
        downloader = (applicationContext as App).downloader
    }
}
