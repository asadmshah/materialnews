package com.asadmshah.materialnews.schedulers

import io.reactivex.Scheduler

interface Schedulers {

    fun io(): Scheduler

    fun main(): Scheduler
}