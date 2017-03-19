package com.asadmshah.materialnews.schedulers

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers

internal class SchedulersImpl : Schedulers {

    override fun io(): Scheduler {
        return io.reactivex.schedulers.Schedulers.io()
    }

    override fun main(): Scheduler {
        return AndroidSchedulers.mainThread()
    }
}